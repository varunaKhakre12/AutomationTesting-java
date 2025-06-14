package org.example.windowHandle;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class AssignmentWindowHandles {

    ChromeDriver driver;

    String url = "https://the-internet.herokuapp.com/windows";

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
        // Store the parent window
        String parentId = driver.getWindowHandle();

        // 1️⃣ Click on first element to open the first child window
        driver.findElement(By.xpath("//div[@class=\"large-4 large-centered columns\"]/div/a")).click();
        Thread.sleep(2000); // Ideally use WebDriverWait

        // 2️⃣ Get first child window ID
        String childId = getNewWindowHandle(Set.of(parentId));

        // Switch to child window and get text
        driver.switchTo().window(childId);
        String text = driver.findElement(By.xpath("//div[@class=\"text--center\"]/a/button")).getText();
        System.out.println("text: " + text);

        // 🔁 Return to parent
        driver.switchTo().window(parentId);

        // 3️⃣ Click to open second child (sub-child) window
        driver.findElement(By.xpath("//div[@class=\"example\"]/a")).click();
        Thread.sleep(2000);

        // 4️⃣ Get sub-child window ID
        String subChildId = getNewWindowHandle(Set.of(parentId, childId));

        // Switch to sub-child window and get text
        driver.switchTo().window(subChildId);
        String textNewWindow = driver.findElement(By.xpath("//div[@class=\"example\"]/h3")).getText();
        System.out.println("textNewWindow: " + textNewWindow);

        // 🔁 Return to parent and get text
        driver.switchTo().window(parentId);
        String textParentWindow = driver.findElement(By.xpath("//div[@class=\"example\"]/h3")).getText();
        System.out.println("textParentWindow: " + textParentWindow);
    }


    public String getNewWindowHandle(Collection<String> existingHandles) {
        Set<String> currentHandles = driver.getWindowHandles();
        for (String handle : currentHandles) {
            if (!existingHandles.contains(handle)) {
                return handle;
            }
        }
        throw new RuntimeException("New window not found.");
    }

    public void closeBrowser() {
//         driver.close(); //to close current browser window
        driver.quit(); //to close all browser windows
    }

}
