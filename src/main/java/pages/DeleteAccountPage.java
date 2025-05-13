package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage {

    @FindBy(css = "h2[data-qa='account-deleted']")
    private WebElement accountDeletedMessage;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public DeleteAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountDeletedMessageVisible() {
        return accountDeletedMessage.isDisplayed();
    }

    public void clickContinueAfterDeletion() {
        continueButton.click();
    }
}
