package org.example.dragNDrop;

import org.example.testProject2.Login;


public class DndMain {
    public static void main(String[] args) throws InterruptedException {

        dnd dragndrop = new dnd();
        dragndrop.invokeBrowser();
        dragndrop.dynamicDropdown1();
        dragndrop.dynamicDropdown2("Ind", "India");
        dragndrop.closeBrowser();
    }

}
