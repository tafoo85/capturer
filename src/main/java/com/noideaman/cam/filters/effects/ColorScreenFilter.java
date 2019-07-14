package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.Filter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ColorScreenFilter extends Filter {

    private Map<Integer, Integer> histogram;
    private Map.Entry<Integer, Integer> maxEntry;
    private Map.Entry<Integer, Integer> maxEntry1;
    private Map.Entry<Integer, Integer> maxEntry2;
    private BufferedImage bgImage;

    public ColorScreenFilter() {
        histogram = new HashMap<>();
        try {
            bgImage = ImageIO.read(ColorScreenFilter.class.getResourceAsStream("/images/bg-tree.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Frame filter(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage image = converter.convert(frame);
        int height = image.getHeight();
        int width = image.getWidth();
        if (maxEntry == null) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int value = image.getRGB(x, y);

                    if (histogram.containsKey(value)) {
                        int frequency = histogram.get(value) + 1;
                        histogram.put(value, frequency);
                    } else {
                        histogram.put(value, 1);
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : histogram.entrySet()) {

                if (maxEntry == null || maxEntry.getValue() < entry.getValue()) {
                    maxEntry = entry;
                } else if (maxEntry1 == null || maxEntry1.getValue() < entry.getValue()) {
                    maxEntry1 = entry;
                } else if (maxEntry2 == null || maxEntry2.getValue() < entry.getValue()) {
                    maxEntry2 = entry;
                }
            }
        }

        return super.filter(frame);
    }

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int pixelValue = image.getRGB(x, y) & 0x00ffffff;
        int pixelMask = maxEntry2.getKey() & 0x00ffffff;

        int redChannel = pixelValue & RED_CHANNEL_MASK;
        int blueChannel = pixelValue & BLUE_CHANNEL_MASK;
        int greenChannel = pixelValue & GREEN_CHANNEL_MASK;

        int maskGreenChannel = pixelMask & GREEN_CHANNEL_MASK;
        int maskRedChannel = pixelMask & RED_CHANNEL_MASK;
        int maskBlueChannel = pixelMask & BLUE_CHANNEL_MASK;

        if (redChannel > maskRedChannel && blueChannel > maskBlueChannel && greenChannel > maskGreenChannel) {
            int pixel = bgImage.getRGB(x, y);
            image.setRGB(x, y, pixel);
        }
    }
}
