package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class GhostFilter implements IFilter {
    private BufferedImage previousFrame;

    public GhostFilter() {

    }

    @Override
    public Frame filter(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.convert(frame);

        int height = image.getHeight();
        int width = image.getWidth();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Integer previousPixel = null;
                if (previousFrame != null) {
                    previousPixel = previousFrame.getRGB(x, y);
                }
                int currentPixel = image.getRGB(x, y);
                if (previousPixel != null) {
                    int previousAlpha = channel(previousPixel, 0xff000000);
                    int previousRed = channel(previousPixel, RED_CHANNEL_MASK);
                    int previousGreen = channel(previousPixel, GREEN_CHANNEL_MASK);
                    int previousBlue = channel(previousPixel, BLUE_CHANNEL_MASK);
                    int alpha = channel(currentPixel, 0xff000000);
                    int red = channel(currentPixel, RED_CHANNEL_MASK);
                    int green = channel(currentPixel, GREEN_CHANNEL_MASK);
                    int blue = channel(currentPixel, BLUE_CHANNEL_MASK);

                    int newRed = channel((int) (red * .1) + (int)(previousRed * .9), RED_CHANNEL_MASK);
                    int newGreen = channel((int)(green * .1) + (int)(previousGreen * .9), GREEN_CHANNEL_MASK);
                    int newBlue = channel((int)(blue * .1) + (int)(previousBlue * .9), BLUE_CHANNEL_MASK);
                    int newAlpha = channel((int)(alpha * .1) + (int)(previousAlpha * .9), 0xff000000);

                    currentPixel = newAlpha + newRed + newGreen + newBlue;
                }
                image.setRGB(x, y, currentPixel);
            }
        }

        previousFrame = image;

        return converter.convert(image);

    }

    private int channel(int color, int mask) {
        return color & mask;
    }
}
