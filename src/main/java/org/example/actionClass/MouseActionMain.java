package org.example.actionClass;


public class MouseActionMain {
    public static void main(String[] args) throws InterruptedException {

        MouseAction mouseAction = new MouseAction();
        mouseAction.invokeBrowser();
        mouseAction.hover();
//        mouseAction.closeBrowser();
    }

}
