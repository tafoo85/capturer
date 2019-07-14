package com.noideaman.cam.filters;

import org.bytedeco.javacv.Frame;

public interface IFilter {
    int RED_CHANNEL_MASK =   0x00ff0000;
    int GREEN_CHANNEL_MASK = 0x0000ff00;
    int BLUE_CHANNEL_MASK =  0x000000ff;

    default Frame filter(Frame frame) {
        return frame;
    }
}
