package org.example.frameLecure;

import org.example.waitsLecture.WaitsTypes;


public class FramesMain {
    public static void main(String[] args) throws InterruptedException {

        FrameHandling frameHandling = new FrameHandling();
        frameHandling.invokeBrowser();
//        frameHandling.linkCount();
        frameHandling.assignment();
//        frameHandling.switchToFrames();
//        frameHandling.nestedFrames();
    }

}
