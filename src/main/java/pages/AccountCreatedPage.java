package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage {

    @FindBy(xpath = "//b[contains(text(),'Account Created!')]")
    private WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[contains(text(),'Continue')]")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountCreatedVisible() {
        return accountCreatedMsg.isDisplayed();
    }

    public void clickContinue() {
        continueButton.click();
    }
}

