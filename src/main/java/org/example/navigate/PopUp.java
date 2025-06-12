package org.example.navigate;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class PopUp {


    ChromeDriver driver;
    String url = "https://demoqa.com/browser-windows";

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //implicitlyWait
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
    public void popUp() throws IOException, InterruptedException {
        WebElement element = driver.findElement(By.xpath("//div//button[@id=\"messageWindowButton\"]"));
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
        Thread.sleep(2000);
        Set<String> handler = driver.getWindowHandles();
        Iterator<String> it = handler.iterator();

        String parentWindowId = it.next();
        System.out.println("parent: "+ parentWindowId);

        String childWindowId = it.next();
        System.out.println("child: "+ childWindowId);

        driver.switchTo().window(childWindowId);
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textMatches(By.xpath("//html//body"), Pattern.compile("Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.")));
        System.out.println("child title: "+ driver.getTitle() + "url :" +driver.getCurrentUrl());

        driver.close();

        driver.switchTo().window(parentWindowId);
        Thread.sleep(1000);
        System.out.println("parent title: "+ driver.getTitle());
    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
