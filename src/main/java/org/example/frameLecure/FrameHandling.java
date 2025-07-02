package org.example.frameLecure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class FrameHandling {


    ChromeDriver driver;
//    String url = "https://www.globalsqa.com/demo-site/frames-and-windows/?utm_source=chatgpt.com"; //lecture
    String url = "https://the-internet.herokuapp.com/nested_frames"; //assignment


    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicitlyWait
        driver.get(url);
        System.out.println(driver.getCurrentUrl());

    }

    public void switchToFrames() {
//        driver.findElement(By.id("a077aa5e"));
        driver.switchTo().frame("globalSqa");
        driver.findElement(By.xpath("//li[@id=\"iFrame\"]")).click();

        driver.findElement(By.xpath("//div[@class=\"info_overlay_padding\"][1]")).click();
        System.out.println("switched");

//        driver.findElement(By.id("a077aa5e"));
    }

    public void nestedFrames() {
        System.out.println("started");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        String text =  driver.findElement(By.xpath("//*[@id=\"content\"]")).getText();
        System.out.println(text);
        System.out.println("ended");

    }



    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
