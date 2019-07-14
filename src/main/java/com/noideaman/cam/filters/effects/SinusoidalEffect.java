package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.Filter;
import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class SinusoidalEffect extends Filter {

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int width = image.getWidth() / 2;
        int height = image.getHeight() / 2;
        int lengthX = width - x;
        int lengthY = height - y;
        if (x > 400 || x < 100) {
            return;
        }

        if (y > 400 || y < 100) {
            return;
        }

        image.setRGB(10 + x, y + 10, image.getRGB(x, y));
    }
}
