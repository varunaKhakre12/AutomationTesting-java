package org.example.dropdown;


public class DdMain {
    public static void main(String[] args) throws InterruptedException {

        Dd dd = new Dd();
        dd.invokeBrowser();
        dd.dynamicDropdown1();
        dd.dynamicDropdown2("Ind", "India");
        dd.closeBrowser();
    }

}
