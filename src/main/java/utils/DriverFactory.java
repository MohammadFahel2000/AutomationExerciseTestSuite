package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
public class DriverFactory {

    // ThreadLocal for parallel execution as requested by Rayan
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            // Initialize driver if not present for the thread
            String browser = ConfigReader.getProperty("browser");

            try {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        driverThreadLocal.set(new FirefoxDriver());
                        break;
                    case "edge":
                        driverThreadLocal.set(new EdgeDriver());
                        break;
                    case "chrome":
                    default:
                        driverThreadLocal.set(new ChromeDriver());
                        break;
                }

                // Common WebDriver settings
                driverThreadLocal.get().manage().window().maximize();
                driverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser + "\n" +
                        "Error: " + e.getMessage(), e);
            }
        }

        return driverThreadLocal.get();
    }

    // Tear down method to quit driver properly
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
