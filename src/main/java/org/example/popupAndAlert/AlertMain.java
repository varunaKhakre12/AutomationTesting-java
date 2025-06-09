package org.example.popupAndAlert;

import org.example.waitsLecture.WaitsTypes;


public class AlertMain {

    public static void main(String[] args) throws InterruptedException {

        AlertLecture alertLecture = new AlertLecture();
        alertLecture.invokeBrowser();
//        alertLecture.getBrowserPopup();
        alertLecture.getWindowsPopup2();
    }

}
