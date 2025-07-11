package org.example.calanderUi;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CalanderHandling {


    ChromeDriver driver;
    String url = "https://rahulshettyacademy.com/seleniumPractise/#/offers"; //assignment


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

    public void assetDate() {
        System.out.println("started");
        String monthNumber = "6";
        String date = "15";
        String year = "2027";
        String[] expDate = {monthNumber, date, year};
        driver.findElement(By.cssSelector(".react-date-picker__calendar-button__icon")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();

        driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
        driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(monthNumber)-1).click();
        driver.findElement(By.xpath("//abbr[text()='"+date+"']")).click();

        List<WebElement> actualDate = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));
        for (int i =0; i<actualDate.size(); i++){
            System.out.println(actualDate.get(i).getAttribute("value"));
            Assert.assertEquals(actualDate.get(i).getAttribute("value"), expDate[i]);
        }
        System.out.println("ended");

    }

    //scroll handling
    public void scrollHandling(){

    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
