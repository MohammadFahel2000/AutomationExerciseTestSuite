package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Link;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.JiraIntegration;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : "No error message";

        String summary = "[Automation Failure] Test: " + testName;
        String description = "Test `" + testName + "` failed.\n\nError:\n" + errorMessage;

        String jiraTicketId = JiraIntegration.createIssue(summary, description);
        if (jiraTicketId != null) {
            Allure.getLifecycle().updateTestCase(testResult -> testResult.getLinks().add(
                    new Link()
                            .setName("Jira Ticket")
                            .setUrl(JiraIntegration.JIRA_BASE_URL + "/browse/" + jiraTicketId)));
            Allure.label("jira", jiraTicketId);
        }
    }
}