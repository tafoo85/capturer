package com.noideaman.cam;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import com.noideaman.ApplicationState;
import com.noideaman.cam.filters.FilterFactory;
import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.*;

public class Capturer {
    private ApplicationState appState;
    private FrameGrabber grabber;
    private CanvasFrame canvasFrame;
    private boolean captured;

    public Capturer(ApplicationState appState) {
        this.appState = appState;
        this.captured = false;
    }

    public double getGamma() {
        return this.grabber.getGamma();
    }

    public void init() {
        try {
            grabber = FrameGrabber.createDefault(0);
            grabber.setImageHeight(1920);
            grabber.setImageWidth(1080);
            grabber.start();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Frame captureFrame() {
        try {
            Frame currentFrame = grabber.grab();
            IFilter filter = FilterFactory.getFilter(appState);

            return filter.filter(currentFrame);

        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    private void writeFrame(Frame currentFrame) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        imwrite("D:\\Projects\\capturer\\capture.png", converter.convert(currentFrame));
    }
}
