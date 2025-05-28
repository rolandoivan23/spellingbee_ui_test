package com.spelling_bee.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * BaseTest class for setting up and tearing down WebDriver instances.
 * This class provides common configurations for test classes.
 */
public class BaseTest {

    // ThreadLocal WebDriver to ensure thread safety if running tests in parallel
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Sets up the WebDriver instance before each test method.
     * It reads the "browser" parameter from testng.xml to determine which browser to use.
     * Defaults to Chrome if no browser parameter is specified or if an unsupported browser is named.
     *
     * @param browser The name of the browser to initialize (e.g., "chrome", "firefox").
     * This parameter is typically passed from the testng.xml file.
     */
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        WebDriver webDriver;
        if (browser == null || browser.isEmpty()) {
            System.out.println("Browser parameter is not specified in testng.xml, defaulting to Chrome.");
            browser = "chrome"; // Default browser
        }

        System.out.println("Setting up browser: " + browser);

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Add any specific Firefox options here
                // firefoxOptions.addArguments("--headless"); // Example: run in headless mode
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add any specific Chrome options here
                // chromeOptions.addArguments("--headless"); // Example: run in headless mode
                // chromeOptions.addArguments("--disable-gpu");
                // chromeOptions.addArguments("--window-size=1920,1080");
                webDriver = new ChromeDriver(chromeOptions);
                break;
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Implicit wait
        webDriver.manage().window().maximize(); // Maximize browser window
        driver.set(webDriver); // Set the driver for the current thread
        System.out.println("WebDriver setup complete for thread: " + Thread.currentThread().getId());
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return WebDriver instance.
     */
    protected WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Tears down the WebDriver instance after each test method.
     * It quits the browser and removes the driver from the ThreadLocal variable.
     */
    @AfterMethod
    public void tearDown() {
        WebDriver webDriver = getDriver();
        if (webDriver != null) {
            System.out.println("Tearing down WebDriver for thread: " + Thread.currentThread().getId());
            webDriver.quit();
        }
        driver.remove(); // Remove the driver for the current thread
        System.out.println("WebDriver teardown complete for thread: " + Thread.currentThread().getId());
    }
}
