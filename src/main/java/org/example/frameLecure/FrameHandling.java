package org.example.frameLecure;

import org.example.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FrameHandling {


    ChromeDriver driver;
    String url = "https://www.globalsqa.com/demo-site/frames-and-windows/?utm_source=chatgpt.com";

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\seleniumProjects\\AutomationTesting-java\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicitlyWait
        driver.get(url);
        System.out.println(driver.getCurrentUrl());

    }

    public void switchToFrames() {
//        driver.findElement(By.id("a077aa5e"));
        driver.switchTo().frame("a077aa5e");
        driver.findElement(By.id("a077aa5e"));
    }
    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
