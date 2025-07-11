package org.example.scrollHandling;

import org.example.calanderUi.CalanderHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScrollHandling {


    ChromeDriver driver;
    String url = "https://rahulshettyacademy.com/AutomationPractice/"; //assignment

    public static void main(String[] args) throws InterruptedException {

        ScrollHandling scrollHandling = new ScrollHandling();
        scrollHandling.invokeBrowser();
        scrollHandling.scroll();
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


    //scroll
    public void scroll() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,580)"); //scroll main window

        //table 1
        js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=500"); //scroll table element - document selects from css elements

        //get 4th col of table and sum its values
        List<WebElement> colValues = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        Integer sum = 0;
        for(WebElement value : colValues){
            sum += Integer.parseInt(value.getText());
        }
        System.out.println(sum);

        //compare and check total sum
        Integer expectedSum = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
        Assert.assertEquals(sum, expectedSum);

        //table 2
        //get 3rd col of table and sum its values
        List<WebElement> colValues2 = driver.findElements(By.cssSelector(".table-display td:nth-child(3)"));
        Integer sum2 = 0;
        for(WebElement value : colValues2){
            sum2 += Integer.parseInt(value.getText());
        }
        System.out.println(sum2);

        //no. of rows, columns and print 2nd row
        List<WebElement> rows = driver.findElements(By.cssSelector(".table-display tr"));
        List<WebElement> cols = driver.findElements(By.xpath("//table[@class=\"table-display\"]/tbody/tr[1]/th"));
        System.out.println("rows: "+ rows.size()+ ", columns: "+ cols.size());
        System.out.println(rows.get(2).getText());


    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
