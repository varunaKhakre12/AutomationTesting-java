package org.example.greenKartExMPLE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class GreenKart {


    ChromeDriver driver;
    String url = "https://rahulshettyacademy.com/seleniumPractise/";

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\seleniumProjects\\AutomationTesting-java\\libs\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //implicitlyWait

        System.out.println(driver.getCurrentUrl());

    }

    public void addToCart() {

        List<WebElement> productList = driver.findElements(By.cssSelector("h4.product-name"));

        String[] itemsNeeded = {"Cucumber", "Brocolli"};
        int count=0;
        for (int i=0; i<productList.size(); i++){
            String[] actualName = productList.get(i).getText().split("-");
            String name = actualName[0].trim();

            List itemList = Arrays.asList(itemsNeeded);
            if(itemList.contains(name)){
                driver.findElements(By.xpath("//div[@class=\"product-action\"]/button")).get(i).click();
                count++;
            }
            if(count>=itemList.size()){
                break;
            }
        }
    }

    public void checkout(String promoCode) {

        //click on cart icon
        driver.findElement(By.xpath("//a[@class=\"cart-icon\"]")).click();

        //click on proceed to checkout
        driver.findElement(By.xpath("//div[@class=\"cart-preview active\"]/div/button")).click();

        //enter promo code
        driver.findElement(By.className("promoCode")).sendKeys(promoCode);
        //click on apply button
        driver.findElement(By.className("promoBtn")).click();

/*
//        wait till "Code applied ..!" text appears
//          use-explicit wait:
//          1. more code (dis-adv.)
//          2. only applicable on a particular element, less performance issues (adv.)
//          3. better to test (adv.)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textMatches(By.className("promoInfo"), Pattern.compile("Code applied ..!")));
*/

        //check if result text matches "Code applied ..!"
        Assert.assertEquals(driver.findElement(By.className("promoInfo")).getText(), "Code applied ..!");

        //click on place order
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        //select a country
        WebElement staticDropdown = driver.findElement(By.tagName("select"));
        Select dropdown = new Select(staticDropdown);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select/option[@value=\"India\"]")));
        dropdown.selectByVisibleText("India"); // xpath = //select/option[@value="India"]

        //tick checkbox
        driver.findElement(By.className("chkAgree")).click();

        //click n proceed
        driver.findElement(By.xpath("//button[text()= \"Proceed\"]")).click();


    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
