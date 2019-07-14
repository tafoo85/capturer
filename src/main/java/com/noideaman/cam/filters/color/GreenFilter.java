package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class GreenFilter extends ColorFilter {
    public GreenFilter() {
        super(GREEN_CHANNEL_MASK);
    }
}
