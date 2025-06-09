package org.example.assignment;


public class Login2Main {
    public static void main(String[] args) throws InterruptedException {

        Login2 greenKart = new Login2();
        greenKart.invokeBrowser();
        greenKart.login();
        greenKart.addToCart();
//        greenKart.closeBrowser();
    }

}
