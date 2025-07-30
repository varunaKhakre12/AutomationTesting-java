package org.example.relativeLocators;

import org.apache.commons.io.FileUtils;
import org.example.scrollHandling.ScrollHandling;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class RelativeLocators {


    ChromeDriver driver;
    String url = "https://rahulshettyacademy.com/angularpractice/";

    public static void main(String[] args) throws InterruptedException, IOException {

        RelativeLocators relativeLocators = new RelativeLocators();
        relativeLocators.invokeBrowser();
//        relativeLocators.locators();
        relativeLocators.handleWindows();

    }

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


    public void locators() {
        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        System.out.println(driver.findElement(with (By.tagName("label")).above (nameEditBox)).getText());

        WebElement dateofBirth = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement (with (By.tagName("input")).below (dateofBirth)).click();

        WebElement iceCreamLabel =driver.findElement(By.xpath("//label [text()='Check me out if you Love IceCreams!']"));
        driver.findElement(with (By.tagName("input")).toLeftOf(iceCreamLabel)).click();

        WebElement rightLabel =driver.findElement(By.id("inlineRadio1"));
        System.out.println(driver.findElement(with (By.tagName("label")).toRightOf (rightLabel)).getText());
    }

    public void handleWindows() throws IOException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
//        driver.switchTo().newWindow(WindowType.TAB);
        driver.switchTo().newWindow(WindowType.WINDOW);
        Set<String> handles=driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        String parentWindowId = it.next();
        String childWindow = it.next();
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");
        String text = driver.findElement(By.xpath("//*[@id=\"courses-block\"]/div[3]/div/div[2]/div[1]/h2/a")).getText();
        System.out.println(text);
        //go to parent window and enter text
        driver.switchTo().window(parentWindowId);
        WebElement nameBox = driver.findElement(By.cssSelector("[name='name']"));
        nameBox.sendKeys(text);

        //get screenshot of component
        File file = nameBox.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("nameTest.png"));

        //get height and width of box
        System.out.println(nameBox.getRect().getDimension().getHeight());
        System.out.println(nameBox.getRect().getDimension().getWidth());
    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }






}
