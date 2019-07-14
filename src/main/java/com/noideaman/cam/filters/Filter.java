package com.noideaman.cam.filters;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public abstract class Filter implements IFilter {

    @Override
    public Frame filter(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.convert(frame);
        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.runAlgorithm(image, x, y);
            }
        }

        return converter.convert(image);
    }

    protected abstract void runAlgorithm(BufferedImage image, int x, int y);
}
