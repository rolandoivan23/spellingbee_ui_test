package com.spelling_bee.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

    @Test(description="Login happy path")
    public void login() throws InterruptedException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(option);
        com.pom.pages.Login loginPage = new com.pom.pages.Login(driver);
        loginPage.login();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.linkText("New Post")).getText(), "New Post");
        driver.quit();
    }
}
