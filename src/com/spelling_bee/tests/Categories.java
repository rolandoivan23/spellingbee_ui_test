package com.spelling_bee.tests;

import com.pom.pages.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.Random;

public class Categories {
    @Test(description = "Create category")
    public void createCategory() throws InterruptedException {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(option);
        Login loginPage = new Login(driver);
        loginPage.login();
        driver.get("https://blog.mexclouds.com/categories/new");
        driver.findElement(By.id("category_name")).wait(1500);
        WebElement nameField = driver.findElement(By.id("category_name"));
        nameField.sendKeys("Created with selenium automation " + new Random().nextInt());
        driver.findElement(By.id("category_description")).sendKeys("this is a test category");

        WebElement tagPopular = driver.findElement(By.id("category_tag_ids_1"));
        WebElement tagTrend = driver.findElement(By.id("category_tag_ids_2"));
        //Actions actions = new Actions(driver);
        //actions.click(tag)
        tagPopular.click();
        tagTrend.click();
        driver.findElement(By.name("commit")).click();
        WebElement successMessage = driver.findElement(By.cssSelector("article.post>p"));
        Assert.assertEquals(successMessage.getText(), "Category was successfully created.");

    }
}
