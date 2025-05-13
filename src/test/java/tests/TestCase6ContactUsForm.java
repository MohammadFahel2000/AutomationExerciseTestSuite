package tests;
import base.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

@Listeners(TestListener.class)
public class TestCase6ContactUsForm extends BaseTest {

    // Annotations:
    @Epic("Automation Exercise")
    @Feature("Contact Us")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the form is sent correctly.")
    @Test(description = "Test Case 6: Contact Us Form Submission")
    public void testSendContactForm() {
        driver.get("https://automationexercise.com/");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSignupLoginVisible(), "Home page isn't visible");
        homePage.clickContactUs();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        Assert.assertTrue(contactUsPage.isGetInTouchTextVisible(), "GetInTouch isn't visible");
        contactUsPage.fillContactForm("MohammadFahel", "m.f123@jodayn.com", "automation project", "test test");
        contactUsPage.uploadFile("src/test/resources/testdata/testUploadImg.png");
        contactUsPage.submitForm();

        Assert.assertTrue(contactUsPage.isSuccessMessageVisible(), "Success message isn't visible");
        contactUsPage.clickHome();
        Assert.assertTrue(homePage.isHomeBtnOrange(), "Home page isn't visible");
    }
}
