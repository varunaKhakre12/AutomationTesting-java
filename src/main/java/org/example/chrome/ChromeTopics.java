package org.example.chrome;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChromeTopics {

    public static void main(String[] args) throws InterruptedException, IOException {

        //for bypassing proxies
/*        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress: 4444");
        options.setCapability ("proxy", proxy);

        //
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/directory/path");
        options.setExperimentalOption("prefs", prefs);

        //blocking popups
        //https://developer.chrome.com/docs/chromedriver/capabilities
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

//        Firefox0ptions options1 new FirefoxOptions();
//        options1.setAcceptInsecureCerts (true);
//        EdgeOptions options2 = new EdgeOptions();

        //for bypassing ssl certificates
        options.setAcceptInsecureCerts(true);*/
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        //if we want to delete only one cookie like session key to close current session and throw to login screen we do this:
        //driver.manage().deleteCookieNamed("sessionKey");
//        driver.get("https://expired.badssl.com/");
//        System.out.println(driver.getTitle());

        //take screenshot
/*
        driver.get("https://www.google.com/");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\autoScreenshot.png"));*/

        //test for broken link/ URL
        // call url and check status code using java methods

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement footDriver = driver.findElement(By.id("gf-BIG")); //limiting web driver scope
        System.out.println("footer count: "+ footDriver.findElements(By.tagName("a")).size());
        List<WebElement> links = footDriver.findElements(By.tagName("a"));
        SoftAssert softAssert = new SoftAssert();
        for (WebElement link : links){
            String url = link.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
//            Assert.assertTrue(responseCode>400, ("broken link found: "+ link.getText()+" response code = "+ responseCode)); //this will stop the code at the 1st broken link and will not proceed so we use softAssert
            softAssert.assertTrue(responseCode<400, ("broken link found: "+ link.getText()+" response code = "+ responseCode));
        }
        softAssert.assertAll();
    }



}
