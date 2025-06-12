package org.example.testProject2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;


public class Login {

    ChromeDriver driver;
    String url = "http://www.fb.com";

    public void login(String userName, String password) {
        WebElement userNameElement = driver.findElement(By.name("uid")); //identifying page element
        userNameElement.sendKeys(userName); //performing operation on element

        driver.findElement(By.name("password")).sendKeys(password); //identifying page element
        driver.findElement(By.name("btnLogin")).click();
    }

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.get(url);
         System.out.println(driver.getCurrentUrl());
         WebElement userNameElement = driver.findElement(By.xpath("//div//a[@data-testid=\"open-registration-form-button\"]"));
        if(userNameElement != null){
            createAcc("Varuna", "khakre", "3", "3", "2003", "1", "6576575757657","123@123");
        }
    }
    public void createAcc(String firstName, String surname, String birthDay, String birthMonth, String birthYear, String gender, String mobno, String password) {
        driver.findElement(By.xpath("//div//a[@data-testid=\"open-registration-form-button\"]")).click();
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(surname);
        Select selDate = new Select(driver.findElement(By.id("day")));
        Select selMonth = new Select(driver.findElement(By.id("month")));
        Select selYear = new Select(driver.findElement(By.id("year")));

        selDate.selectByVisibleText("21");
        selMonth.selectByVisibleText("Jun");
        selYear.selectByVisibleText("1989");

        driver.findElement(By.xpath("//div/span//label//input[@value='" + gender + "']")).click();
        driver.findElement(By.name("reg_email__")).sendKeys(mobno);
        driver.findElement(By.name("reg_passwd__")).sendKeys(password);
        driver.findElement(By.name("websubmit")).click();
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
