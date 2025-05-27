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
        driver.get("https://blog.mexclouds.com");
        driver.findElement(By.linkText("Login")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("email_address")).sendKeys("rolando.vazquez@hey.com");
        driver.findElement(By.id("password")).sendKeys("123pum");

        driver.findElement(By.cssSelector("input[value=\"Sign in\"]")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.linkText("New Post")).getText(), "New Post");
        driver.quit();
    }
}
