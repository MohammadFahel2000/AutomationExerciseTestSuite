package base;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;
import org.testng.ITestResult;

@Listeners({AllureTestNg.class})
public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void setAllureResultsDirectory() {
        System.setProperty("allure.results.directory", "target/allure-results");
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // I need the browser to be opened for all cases:
//          driver.quitDriver();
        }
    }
}

