package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;

import java.awt.image.BufferedImage;

public class BlackWhiteFilter extends Filter {
    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int currentPixel = image.getRGB(x, y);
        if (currentPixel > 0xff666fff) {
            currentPixel = 0xffffffff;
        } else {
            currentPixel = 0x00000000;
        }
        image.setRGB(x, y, currentPixel);
    }
}
