package org.example.actionClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MouseAction {

    ChromeDriver driver;

    String url = "https://google.com/";

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

    public void hover() throws InterruptedException {
        Actions mouseAction = new Actions(driver);
//        mouseAction.moveToElement(driver.findElement(By.xpath("//div/p[text() = \"Resources\"]"))).build().perform();
        mouseAction
                .moveToElement(driver.findElement(By.className("gLFyf")))
                .click()
                .sendKeys("Varuna Khakre", Keys.ENTER)
                .build()
                .perform();
        System.out.println("enteredd");
    }


    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }

}
