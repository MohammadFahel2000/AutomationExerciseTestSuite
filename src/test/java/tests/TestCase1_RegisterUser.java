package tests;

import base.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelReader;
import utils.ExcelWriter;
import utils.GenerateEmails;
import java.util.Map;

@Listeners(TestListener.class)
public class TestCase1_RegisterUser extends BaseTest {
    
    // Annotations:
    @Epic("Automation Exercise")
    @Feature("Register User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user is registered then deleted.")
    @Test(description = "Test Case 1: Register User")
    public void testRegisterAndDeleteUser() {
        /*
 TODO: The below statement is to test the Jira integration:
        Assert.assertTrue(false, "Intentionally failing this test");
*/
        driver.get("https://automationexercise.com/");


        // 1. Read test data from Excel
        Map<String, String> data = ExcelReader.getRowData();


        // 2. Extract variables
        Assert.assertNotNull(data);
        String username = data.get("username");
        String email = GenerateEmails.generateUniqueEmail(); // unique email
        String password = data.get("password");
        String day = data.get("day");
        String month = data.get("month");
        String year = data.get("year");
        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String address = data.get("address");
        String country = data.get("country");
        String state = data.get("state");
        String city = data.get("city");
        String zip = data.get("zip");
        String phone = data.get("phone");

        // 3. Start test steps
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSignupLoginVisible(), "Signup/Login link not visible");

        homePage.clickSignupLogin();
        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "New User Signup text missing");

        System.out.println("Data: " + data);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);

        Assert.assertNotNull(username, "Username is null");
        Assert.assertFalse(username.trim().isEmpty(), "Username is empty");

        Assert.assertNotNull(email, "Email is null");
        Assert.assertFalse(email.trim().isEmpty(), "Email is empty");

        signupPage.signup(username, email);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm(password, day, month, year,
                firstName, lastName, address, country, state, city, zip, phone);

        Assert.assertTrue(registrationPage.isAccountCreatedVisible(), "Account creation failed");

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.clickContinue();

        homePage.clickDeleteAccount();
        DeleteAccountPage accountDeletedPage = new DeleteAccountPage(driver);
        Assert.assertTrue(accountDeletedPage.isAccountDeletedMessageVisible(), "Account deletion failed");

        accountDeletedPage.clickContinueAfterDeletion();
        
        ExcelWriter.writeValueToExcel(email);
    }
}
