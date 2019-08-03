package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.Filter;
import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class TrippyEffect extends GhostFilter {
    protected double PREVIOUS_RED_WEIGHT = .9;
    protected double CURRENT_RED_WEIGHT = .3;
    protected double CURRENT_BLUE_WEIGHT = .9;
    protected double PREVIOUS_BLUE_WEIGHT = .3;

    @Override
    public Frame filter(Frame capturedFrame) {
        super.PREVIOUS_RED_WEIGHT = PREVIOUS_RED_WEIGHT;
        super.CURRENT_RED_WEIGHT = CURRENT_RED_WEIGHT;
        super.PREVIOUS_BLUE_WEIGHT = PREVIOUS_BLUE_WEIGHT;
        super.CURRENT_BLUE_WEIGHT = CURRENT_BLUE_WEIGHT;
        return super.filter(capturedFrame);
    }

}
