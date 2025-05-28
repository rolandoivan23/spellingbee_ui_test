package com.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void login(){
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
    }
}
