package org.example.testProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;


public class Guru99Form {

    ChromeDriver driver;
    String url = "https://demo.guru99.com/V1/index.php";

    public void login(String userName, String password) {
        WebElement userNameElement = driver.findElement(By.name("uid")); //identifying page element
        userNameElement.sendKeys(userName); //performing operation on element

        driver.findElement(By.name("password")).sendKeys(password); //identifying page element
        driver.findElement(By.name("btnLogin")).click();
    }
    public void addCustomer(String name, String dob, String address, String city, String state, String pinno, String tele, String email) {
        driver.findElement(By.linkText("New Customer")).click();
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.xpath("//td/input[@type=\"radio\"][2]")).click();
        driver.findElement(By.name("dob")).sendKeys(dob);
        driver.findElement(By.name("addr")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pinno);
        driver.findElement(By.name("telephoneno")).sendKeys(tele);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("sub")).click();
    }

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.get(url);
     }

    public Boolean checkTitle() {
        if (Objects.equals(driver.getTitle(), "Uptut | Corporate IT Training & Consulting Services")) {
            System.out.println("PASS");
            return true;
        } else {
            System.out.println("FAIL");
            return false;
        }
    }

    public void navigation() {

        driver.navigate().to("https://www.facebook.com");
        driver.navigate().back();
        String urlFromWebpage = driver.getCurrentUrl();
        System.out.println(urlFromWebpage);
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
         driver.quit(); //to close all browser windows
     }

}
