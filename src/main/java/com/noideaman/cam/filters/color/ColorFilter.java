package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;

import java.awt.image.BufferedImage;

public abstract class ColorFilter extends Filter {
    private int mask;
    public ColorFilter(int mask) {
        this.mask = mask;
    }

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int pixelValue = image.getRGB(x, y);
        image.setRGB(x, y, this.mask & pixelValue);
    }
}
