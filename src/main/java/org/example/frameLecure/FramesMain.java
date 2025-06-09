package org.example.frameLecure;

import org.example.waitsLecture.WaitsTypes;


public class FramesMain {
    public static void main(String[] args) {

        FrameHandling frameHandling = new FrameHandling();
        frameHandling.invokeBrowser();
        frameHandling.switchToFrames();
    }

}
