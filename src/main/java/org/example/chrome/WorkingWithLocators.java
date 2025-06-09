package org.example.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;


public class WorkingWithLocators {

    WebDriver driver;
    String url = "https://rahulshettyacademy.com/locatorspractice/";

    public void login() {

    }

    public void invokeBrowser() {
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\seleniumProjects\\AutomationTesting-java\\libs\\chromedriver.exe");
         driver = new ChromeDriver();
         driver.manage().window().maximize();
     }


    public void fillForm() throws InterruptedException {
        driver = new ChromeDriver();
        String name = "varuna";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String password = getPassword(driver);
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys(name);
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        driver.findElement(By.className("signInBtn")).click();
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.tagName("p")).getText());
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(),"Hello "+name+",");
        driver.findElement(By.xpath("//*[text()='Log Out']")).click();
    }

    public void parentChildXpath() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[2]")).getText());
        System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/parent::header/a/following-sibling::a")).getText());


    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
         driver.quit(); //to close all browser windows
     }


    public static String getPassword(WebDriver driver) throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.linkText("Forgot your password?")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        String passwordText =driver.findElement(By.cssSelector("form p")).getText();
        //Please use temporary password 'rahulshettyacademy' to Login.
        String[] passwordArray = passwordText.split("'");
        // String[] passwordArray2 = passwordArray[1].split("'");
        // passwordArray2[0]
        String password = passwordArray[1].split("'")[0];
        return password;
        //0th index - Please use temporary password
        //1st index - rahulshettyacademy' to Login.

        //0th index - rahulshettyacademy
        //1st index - to Login.

    }

    public static void main(String[] args) throws InterruptedException {
        WorkingWithLocators workingWithLocators = new WorkingWithLocators();
        workingWithLocators.invokeBrowser();
//        workingWithLocators.fillForm();
        workingWithLocators.parentChildXpath();
        workingWithLocators.closeBrowser();
    }
}
