package org.example.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Login2 {


    ChromeDriver driver;

    String url = "https://rahulshettyacademy.com/loginpagePractise/";


    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //implicitlyWait

        System.out.println(driver.getCurrentUrl());

    }

    public void login() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String userName = driver.findElement(By.xpath("//b/i")).getText();
        String password = driver.findElement(By.xpath("//b/following-sibling::b/i")).getText();

        System.out.println(userName+ " "+password);

        //enter username
        driver.findElement(By.id("username")).sendKeys(userName);

        //enter password
        driver.findElement(By.id("password")).sendKeys(password);

        //okay- alert
        driver.findElement(By.cssSelector(".customradio:nth-child(2)")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();

        //select dropdown
        Thread.sleep(3000);
        driver.findElement(By.xpath("//select[@class=\"form-control\"]")).click();
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@class=\"form-control\"]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select/option[@value=\"teach\"]")));
        dropdown.selectByValue("teach");

        //tnc
        driver.findElement(By.id("terms")).click();

        //sign in
        driver.findElement(By.id("signInBtn")).click();

    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class=\"card-footer\"]/button"));

        for (int i=0; i<productList.size(); i++){
            driver.findElements(By.xpath("//div[@class=\"card-footer\"]/button")).get(i).click();
        }

        //checkout
        driver.findElement(By.cssSelector("a.nav-link.btn.btn-primary")).click();

        //checkout2
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();

        //select a country
        driver.findElement(By.id("country")).sendKeys("ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"suggestions\"]/ul/li/a[text()= \"India\"]")));
        driver.findElement(By.xpath("//div[@class=\"suggestions\"]/ul/li/a[text()= \"India\"]")).click();

        //checkbox
        driver.findElement(By.xpath("//div//input[@id=\"checkbox2\"]")).click();

        //submit
        driver.findElement(By.cssSelector("input.btn.btn-success.btn-lg")).click();

    }


    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
