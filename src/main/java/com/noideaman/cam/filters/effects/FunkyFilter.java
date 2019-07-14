package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.Filter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class FunkyFilter extends Filter {

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int rbgValue = image.getRGB(x, y);
        rbgValue = rbgValue & 0xffacacac;
        image.setRGB(x, y, rbgValue);
    }
}
