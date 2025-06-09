package org.example.greenKartExMPLE;


public class GreenKartMain {
    public static void main(String[] args) throws InterruptedException {

        GreenKart greenKart = new GreenKart();
        greenKart.invokeBrowser();
        greenKart.addToCart();
        greenKart.checkout("rahulshettyacademy");
//        greenKart.closeBrowser();
    }

}
