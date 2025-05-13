package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(css = "div.signup-form h2")
    private WebElement newUserSignupText;

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement nameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isNewUserSignupVisible() {
        return newUserSignupText.isDisplayed();
    }

    public void signup(String name, String email) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        signupButton.click();
    }
}
