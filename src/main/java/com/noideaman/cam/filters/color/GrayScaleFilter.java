package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;

import java.awt.image.BufferedImage;

public class GrayScaleFilter extends Filter {

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int currentPixel = image.getRGB(x, y);
        int redValue = currentPixel & RED_CHANNEL_MASK;
        int greenValue = redValue >>> 8;
        int blueValue = redValue >>> 16;
        int newPixel = redValue + greenValue + blueValue;
        image.setRGB(x, y, newPixel);
    }
}
