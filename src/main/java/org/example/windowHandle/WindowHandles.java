package org.example.windowHandle;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class WindowHandles {

    ChromeDriver driver;

    String url = "https://rahulshettyacademy.com/loginpagePractise/#";

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\varunaProjects\\selenium\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //implicitlyWait
        System.out.println(driver.getCurrentUrl());

    }

    public void window() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/a[1]")).click();
        Set<String> windows = driver.getWindowHandles(); // [parent(0), child(1), subChild(2)]
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);

        String textEmail = driver.findElement(By.xpath("//p/strong/a")).getText();
        System.out.println("email: "+textEmail);
    }



    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }

}
