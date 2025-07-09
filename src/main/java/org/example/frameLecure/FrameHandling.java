package org.example.frameLecure;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FrameHandling {


    ChromeDriver driver;
//    String url = "https://www.globalsqa.com/demo-site/frames-and-windows/?utm_source=chatgpt.com"; //lecture
//    String url = "https://the-internet.herokuapp.com/nested_frames"; //assignment
    String url = "http://qaclickacademy.com/practice.php"; //assignment


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

    public void switchToFrames() {
//        driver.findElement(By.id("a077aa5e"));
        driver.switchTo().frame("globalSqa");
        driver.findElement(By.xpath("//li[@id=\"iFrame\"]")).click();

        driver.findElement(By.xpath("//div[@class=\"info_overlay_padding\"][1]")).click();
        System.out.println("switched");

//        driver.findElement(By.id("a077aa5e"));
    }
    public void linkCount() {

        System.out.println("count: "+ driver.findElements(By.tagName("a")).size() );

        WebElement footDriver = driver.findElement(By.id("gf-BIG")); //limiting web driver scope
        System.out.println("footer count: "+ footDriver.findElements(By.tagName("a")).size());

        WebElement footDriverRow = footDriver.findElement(By.xpath("//*[@id=\"gf-BIG\"]/table/tbody/tr/td[1]")); //limiting web driver scope 2
        System.out.println("footer count: "+ footDriverRow.findElements(By.tagName("a")).size());
        List<WebElement> links = footDriverRow.findElements(By.tagName("a"));
        for (WebElement link : links){
            link.sendKeys(Keys.CONTROL, Keys.ENTER);
        }
        Set<String> windows = driver.getWindowHandles(); // [parent(0), child(1), subChild(2)]
        Iterator<String> it = windows.iterator();
        String childId = null;
        while(it.hasNext()){
            childId = it.next();
            driver.switchTo().window(childId);
            System.out.println("tab title = "+ driver.getTitle());
        }

    }

    public void assignment() throws InterruptedException {
    //1. select 1 checkbox
        WebElement label = driver.findElement(By.xpath("//*[@id=\"checkbox-example\"]/fieldset/label[2]"));
        label.findElement(By.id("checkBoxOption2")).click();

    //2. get label of it
        String optionText = label.getText();
        System.out.println("TEXT:" + optionText);

    //3. select an option in dropdown (option to select comes from 2.)
        WebElement staticDropdown = driver.findElement(By.id("dropdown-class-example"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByContainsVisibleText(optionText);
        System.out.println("dropdown selected");
    //4. enter step2 label text in textbox
        driver.findElement(By.id("name")).sendKeys(optionText);
        Thread.sleep(1000);
    //5. click alert and verify if text of step 2 present in alert popup
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());
        if(alert.getText().contains(optionText)){
            System.out.println("correct msg: "+ alert.getText());
        }else{
            System.out.println("incorrect msg: "+ alert.getText());
        }
        alert.accept();
    }
    public void nestedFrames() {
        System.out.println("started");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        String text =  driver.findElement(By.xpath("//*[@id=\"content\"]")).getText();
        System.out.println(text);
        System.out.println("ended");

    }



    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
