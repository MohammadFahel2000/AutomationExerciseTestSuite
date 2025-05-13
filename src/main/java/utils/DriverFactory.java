package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.UUID;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();

            try {
                switch (browser) {
                    case "firefox":
                        driverThreadLocal.set(new FirefoxDriver());
                        break;
                    case "edge":
                        driverThreadLocal.set(new EdgeDriver());
                        break;
                    case "chrome":
                    default:
                        driverThreadLocal.set(createChromeDriver());
                        break;
                }

                WebDriver driver = driverThreadLocal.get();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser + "\n" +
                        "Error: " + e.getMessage(), e);
            }
        }
        return driverThreadLocal.get();
    }

    private static ChromeDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--user-data-dir=/tmp/chrome_profile_" + UUID.randomUUID()
        );

        // CI-specific configuration
        if (isRunningInCI()) {
            options.setBinary("/usr/bin/google-chrome");
            options.addArguments("--remote-allow-origins=*");
        }

        return new ChromeDriver(options);
    }

    private static boolean isRunningInCI() {
        return System.getenv("CI") != null && System.getenv("CI").equals("true");
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}