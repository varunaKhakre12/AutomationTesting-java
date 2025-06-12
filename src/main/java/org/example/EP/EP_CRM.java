package org.example.EP;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EP_CRM {

    ChromeDriver driver;

    String url = "https://ep-crm.octopi-labs.com/";

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
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

        String userName = "agent1@gmail.com";
        String password = "Welcome01#";
        String epid = "EP0000178";

        System.out.println(userName+ " "+password);

        //enter username
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(userName);

        //enter password
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);

        //submit button
        driver.findElement(By.xpath("//button")).click();

        //click on support
        driver.findElement(By.xpath("//a[@href=\"/support\"]")).click();

        //select search-filter dropdown
        driver.findElement(By.className("responsive-select")).click();
        Select dropdown = new Select(driver.findElement(By.className("responsive-select")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select/option[@value=\"id\"]")));
        dropdown.selectByValue("id");

        //search - input
        driver.findElement(By.className("col-1-1")).sendKeys(epid);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("results")));
        WebElement scrollElement = driver.findElement(By.className("results")); // Replace with your element locator

        // Scroll horizontally to the element
        JavascriptExecutor js = driver;
        js.executeScript("return arguments[0].scrollLeft;", scrollElement);

        Thread.sleep(2000);

        //click on view of 1st result
        driver.findElement(By.cssSelector("button.tertiary.tertiary-view")).click();

    }

    public void addBeneficiary() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String otp = "555555";

        //switch to tab beneficiary
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr")));
        driver.findElement(By.xpath("//p[text()=\"Beneficiary\"]")).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//table/tbody/tr"))));

        //click add+
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Add Beneficiary\"]")));
        driver.findElement(By.xpath("//span[text()=\"Add Beneficiary\"]")).click();

        //wait for otp
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("box"))));

        //enter otp
        driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys(otp);

        //submit otp
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        //fill form

    }


    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }

}
