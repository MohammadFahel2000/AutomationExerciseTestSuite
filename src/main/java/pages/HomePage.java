package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginLink;

    @FindBy(css = "li > a[href='/']")
    private WebElement homeBtn;

    @FindBy(css = "a[href='/contact_us']")
    private WebElement contactUsLink;

    @FindBy(xpath = "//a[text()=' Products']")
    private WebElement productsBtn;

    @FindBy(css = "a[href='/delete_account']")
    private WebElement deleteAccountLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSignupLoginVisible() {
        return signupLoginLink.isDisplayed();
    }

    public void clickSignupLogin() {
        signupLoginLink.click();
    }

    public boolean isHomeBtnOrange() {
        String color = homeBtn.getCssValue("color");
        return color.equals("rgba(255, 165, 0, 1)");
    }

    public void clickContactUs() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(contactUsLink))
                .click();
    }

    public void clickProducts() {
        productsBtn.click();
    }

    public void clickDeleteAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteAccountLink))
                .click();
    }
}
