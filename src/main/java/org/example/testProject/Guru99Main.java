package org.example.testProject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;


public class Guru99Main {
    public static void main(String[] args) {
//        Guru99Login guru99Project = new Guru99Login();
//        guru99Project.invokeBrowser();
//        guru99Project.login("mngr604637", "dYhEbap");
        Guru99Form guru99Form = new Guru99Form();
        guru99Form.invokeBrowser();
        guru99Form.login("mngr604637", "dYhEbap");

        guru99Form.addCustomer("Varuna", "06/11/2012", "2e city", "bhopal", "mp", "438545", "23423423423", "ej@sm.com");

    }

}
