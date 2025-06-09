package org.example.testProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;


public class Guru99Login {

    ChromeDriver driver;
    String url = "https://demo.guru99.com/V1/index.php";

    public void login(String userName, String password) {
        WebElement userNameElement = driver.findElement(By.name("uid")); //identifying page element
        userNameElement.sendKeys(userName); //performing operation on element

        driver.findElement(By.name("password")).sendKeys(password); //identifying page element
        driver.findElement(By.name("btnLogin")).click();
    }

    public void invokeBrowser() {
         System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\seleniumProjects\\AutomationTesting-java\\libs\\chromedriver.exe");
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
