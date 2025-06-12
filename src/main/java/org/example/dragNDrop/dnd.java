package org.example.dragNDrop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class dnd {


    ChromeDriver driver;
    String url = "https://rahulshettyacademy.com/dropdownsPractise/";

    @Value("${chrome.driver.location}")
    private String chromeDriverPath;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); //pageLoadTimeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicitlyWait

        System.out.println(driver.getCurrentUrl());

    }

    public void mouseHover() {
//        driver.switchTo().frame(3);
//        driver.findElement(By.xpath("//div[@class=\"ui-widget-content ui-state-default ui-droppable\"]"));
//        driver.findElement(By.xpath("//li//img[1]"));
//        System.out.println("Found");
//        WebElement image = driver.findElement(By.xpath("//li//img[1]"));
//        WebElement imageDrop = driver.findElement(By.xpath("//div[@class=\"ui-widget-content ui-state-default ui-droppable\"]"));
        //absolute xpath- not recommended
        //1. performance issue
        //2. not reliable
        //3 can be changed anytime in future
//        driver.findElement(By.xpath("//div//button[@id=\"u_0_5\"]/div/div[2]/table/tbody/tr/td[2]"));
        //customised xpath
//        driver.findElement(By.xpath("//div//button[contains(@id, \"u_0_5\")]"));
//        System.out.println("contains found");
//        driver.findElement(By.xpath("//div//button[starts-with(@name, \"lo\")]"));
//        System.out.println("starts-with found");
//        //for link
//        driver.findElement(By.xpath("//div//a[contains(text(), \"Forgotten password?\")]"));
//        System.out.println("link text found");
        //count of links
        List<WebElement> listOfLink = driver.findElements(By.tagName("a"));
        System.out.println(listOfLink.size());
        listOfLink.stream().forEach(webElement -> {
            System.out.println(webElement.getText());
        });


//        Actions action = new Actions(driver);
//        action.clickAndHold(image).moveToElement(imageDrop).release().build().perform();
    }

    public void staticDropdown(){
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        //select dropdown with select tag - static dropdown - functions only work for select tag
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(1);
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("AED");
        System.out.println(dropdown.getFirstSelectedOption().getText());
    }
    public void dynamicDropdown1() throws InterruptedException {
//        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value=\"BHO\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();

    }
    public void dynamicDropdown2(String input, String desiredSelect) throws InterruptedException {
//        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        //select country
        driver.findElement(By.xpath("//input[@id=\"autosuggest\"]")).sendKeys(input);
        Thread.sleep(3000);
        List<WebElement> elementsList = driver.findElements(By.cssSelector("li[class=\"ui-menu-item\"] a"));
        for(WebElement element : elementsList){
            System.out.println(element.getText());
            if (element.getText().equals(desiredSelect)){
                System.out.println(element.getText()+ " found");
                element.click();
                break;
            }
        }

        //select date
        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
        driver.findElement(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr[3]/td[@data-month=\"4\"][@data-year=\"2019\"][3]")).click();

        driver.findElement(By.id("ctl00_mainContent_view_date2")).click();
        driver.findElement(By.xpath("//table[@class=\"ui-datepicker-calendar\"]/tbody/tr[5]/td[@data-month=\"4\"][@data-year=\"2019\"][5]")).click();

        //select passengers
        driver.findElement(By.id("divpaxinfo")).click();
        for(int i=0;i<5;i++) {
            driver.findElement(By.xpath("//div/div/span[@id=\"hrefIncAdt\"]")).click();
        }
        for(int i=0;i<3;i++) {
            driver.findElement(By.xpath("//div/div/span[@id=\"hrefIncChd\"]")).click();
        }

        //select currency
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(1);
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("INR");

        //select checkbox
        driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());
        List<WebElement> elementList = driver.findElements(By.cssSelector("input[type=\"checkbox\"]"));
        System.out.println(elementList.size());

        //select search button
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();

    }
    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }



}
