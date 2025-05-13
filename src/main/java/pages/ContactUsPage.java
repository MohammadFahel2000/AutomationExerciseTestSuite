package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ContactUsPage {
    private WebDriver driver;

    @FindBy(css = "h2.title.text-center")
    private WebElement getInTouchText;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "subject")
    private WebElement subjectInput;

    @FindBy(name = "message")
    private WebElement messageInput;

    @FindBy(name = "upload_file")
    private WebElement uploadFileInput;

    @FindBy(name = "submit")
    private WebElement submitFormBtn;

    @FindBy(css = "div.status.alert.alert-success")
    private WebElement successMessage;

    @FindBy(css = "#form-section a.btn.btn-success")
    private WebElement homeBtn;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isGetInTouchTextVisible() {
        return getInTouchText.isDisplayed();
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        subjectInput.sendKeys(subject);
        messageInput.sendKeys(message);
    }

    public void uploadFile(String filePath) {
        uploadFileInput.sendKeys(new File(filePath).getAbsolutePath());
    }

    public void submitForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitFormBtn);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessMessageVisible() {
        return successMessage.isDisplayed();
    }

    public void clickHome() {
        homeBtn.click();
    }
}
