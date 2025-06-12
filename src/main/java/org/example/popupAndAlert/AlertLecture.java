package org.example.popupAndAlert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;


public class AlertLecture {

    ChromeDriver driver;
//    String url = "https://mail.rediff.com/cgi-bin/login.cgi";
//    String url = "https://html.com/input-type-file/";
    String url = "https://rahulshettyacademy.com/AutomationPractice/";

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.get(url);
         System.out.println(driver.getCurrentUrl());
    }

    public void getBrowserPopup() throws InterruptedException {
        // since this is a popup and we cannot find it in xpath or any other locators so this is how we come here.
        driver.findElement(By.name("login")).sendKeys("Hello");
        driver.findElement(By.name("proceed")).click();
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        if(alert.getText().equals("Please enter a valid user name")){
            System.out.println("correct msg");
        }else{
            System.out.println("incorrect msg");
        }
        alert.accept(); //for OK
//        alert.dismiss(); //for CANCEL

    }

    public void getWindowsPopup() throws InterruptedException {
        // It's a popup (like file upload) which uses windows(OS) interface which selenium cant access
        //check for type=file , don't click on browse button
        driver.findElement(By.name("fileupload")).sendKeys("C:\\Users\\Dell\\Downloads\\blank.pdf");

    }
    public void getWindowsPopup2() throws InterruptedException {
        // It's a popup (like alert) which uses windows(OS) interface which selenium cant access
        driver.findElement(By.id("name")).sendKeys("Rahhhul");
        driver.findElement(By.id("alertbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
    }
    public void closeBrowser() {
//         driver.close(); //to close current browser window
         driver.quit(); //to close all browser windows
     }

}
