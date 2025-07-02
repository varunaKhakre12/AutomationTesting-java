package org.example.navigate;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Navigation {


    ChromeDriver driver;
    String url = "https://www.fb.com";


    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicitlyWait
        driver.get(url);
        System.out.println(driver.getCurrentUrl());

    }

    public void navigate() {
        driver.navigate().to("https://www.google.com");
        driver.navigate().back();
        driver.navigate().refresh();
    }
    public void screenshot() throws IOException {
        // take a screenshot and store in file
        File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //copy screenshot to desired file location
        FileUtils.copyFile(src, new File("C:\\Users\\Dell\\seleniumProjects\\AutomationTesting-java\\src\\main\\java\\org\\example\\navigate\\screenshot.png"));

    }
    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
