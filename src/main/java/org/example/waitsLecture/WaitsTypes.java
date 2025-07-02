package org.example.waitsLecture;

import org.example.waitsLecture.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class WaitsTypes {

    ChromeDriver driver;
    String url = "https://www.flipkart.com/";


    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //implicitlyWait
        driver.get(url);
         System.out.println(driver.getCurrentUrl());

    }

    public void mouseHover() {
        //explicit wait
        WaitUtils.waitTillElementVisible(driver, By.xpath("//span[text() = 'Electronics']"), Duration.ofSeconds(1));
        WebElement electronicsLink = driver.findElement(By.xpath("//span[text() = 'Electronics']"));

        Actions action = new Actions(driver);
        action.moveToElement(electronicsLink).build().perform();

        WaitUtils.waitTillElementVisible(driver, By.xpath("(//a[@class=\"_3490ry\"])[6]"), Duration.ofSeconds(1));
        WebElement soundbarLink = driver.findElement(By.xpath("(//a[@class=\"_3490ry\"])[6]"));
        action.moveToElement(soundbarLink).click().build().perform();
    }
    public void closeBrowser() {
//         driver.close(); //to close current browser window
         driver.quit(); //to close all browser windows
     }

}
