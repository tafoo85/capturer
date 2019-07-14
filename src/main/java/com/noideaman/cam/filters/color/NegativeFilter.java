package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;

import java.awt.image.BufferedImage;

public class NegativeFilter extends Filter {

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int pixelValue = image.getRGB(x, y);
        image.setRGB(x, y, ~pixelValue);
    }
}
