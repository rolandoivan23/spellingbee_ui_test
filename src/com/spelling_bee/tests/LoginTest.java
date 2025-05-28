package com.spelling_bee.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(description="Login happy path")
    public void login() throws InterruptedException {
        WebDriver driver = getDriver();
        com.pom.pages.Login loginPage = new com.pom.pages.Login(driver);
        loginPage.login();
        WebElement newPostBtn = driver.findElement(By.linkText("New Post"));
        Assert.assertEquals(newPostBtn.getText(), "New Post");
    }
}
