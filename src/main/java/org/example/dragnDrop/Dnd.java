package org.example.dragnDrop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dnd {
    ChromeDriver driver;
    String url = "https://www.globalsqa.com/demo-site/draganddrop/";

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicitlyWait

        System.out.println(driver.getCurrentUrl());

    }

    public void mouseHover() {
        driver.switchTo().frame(3);
        driver.findElement(By.xpath("//div[@class=\"ui-widget-content ui-state-default ui-droppable\"]"));
        driver.findElement(By.xpath("//li//img[1]"));
        System.out.println("Found");
        WebElement image = driver.findElement(By.xpath("//li//img[1]"));
        WebElement imageDrop = driver.findElement(By.xpath("//div[@class=\"ui-widget-content ui-state-default ui-droppable\"]"));

        Actions action = new Actions(driver);
        action.clickAndHold(image).moveToElement(imageDrop).release().build().perform();

        //absolute xpath- not recommended
        //1. performance issue
        //2. not reliable
        //3 can be changed anytime in future

        image = driver.findElement(By.xpath("//*[@id=\"trash\"]/ul/li/img"));
        imageDrop = driver.findElement(By.xpath("//*[@id=\"gallery\"]"));
        action.clickAndHold(image).moveToElement(imageDrop).release().build().perform(); //reverse
    }


}
