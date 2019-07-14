package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class HorizontalMirror implements IFilter {
    @Override
    public Frame filter(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.convert(frame);
        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = 0; x < width/2; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(width - x - 1, y, image.getRGB(x, y));
            }
        }

        return converter.convert(image);
    }
}
