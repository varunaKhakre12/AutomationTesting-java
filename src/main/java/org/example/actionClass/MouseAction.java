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
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MouseAction {

    ChromeDriver driver;

    String url = "https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_g50zekzm1_e&adgrpid=155259812313&hvpone=&hvptwo=&hvadid=674842289404&hvpos=&hvnetw=g&hvrand=13126720172771540286&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007768&hvtargid=kwd-29089120&hydadcr=5496_2359482&gad_source=1";


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
                .moveToElement(driver.findElement(By.id("twotabsearchtextbox")))
                .click()
                .sendKeys("Varuna Khakre", Keys.ENTER)
                .build() //to build this string
                .perform(); //to perform action of this string
        System.out.println("enteredd");

        mouseAction.moveToElement(driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/a/span"))).contextClick().build().perform();
    }



    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }

}
