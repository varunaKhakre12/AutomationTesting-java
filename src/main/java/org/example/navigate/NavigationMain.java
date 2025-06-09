package org.example.navigate;

import org.example.dragNDrop.dnd;

import java.io.IOException;


public class NavigationMain {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Navigation navigation = new Navigation();
//        navigation.invokeBrowser();
//        navigation.navigate();
//        navigation.screenshot();

        PopUp popUp = new PopUp();
        popUp.invokeBrowser();
        popUp.popUp();
    }

}
