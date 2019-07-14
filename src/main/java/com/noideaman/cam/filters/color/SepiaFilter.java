package com.noideaman.cam.filters.color;

import com.noideaman.cam.filters.Filter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

public class SepiaFilter extends Filter {

    @Override
    protected void runAlgorithm(BufferedImage image, int x, int y) {
        int p = image.getRGB(x,y);

        int a = (p>>24)&0xff;
        int R = (p>>16)&0xff;
        int G = (p>>8)&0xff;
        int B = p&0xff;

        //calculate newRed, newGreen, newBlue
        int newRed = (int)(0.393*R + 0.769*G + 0.189*B);
        int newGreen = (int)(0.349*R + 0.686*G + 0.168*B);
        int newBlue = (int)(0.272*R + 0.534*G + 0.131*B);

        //check condition
        R = newRed > 255 ? 255: newRed;
        G = newGreen > 255 ? 255: newGreen;
        B = newBlue > 255 ? 255: newBlue;

        //set new RGB value
        p = (a<<24) | (R<<16) | (G<<8) | B;

        image.setRGB(x, y, p);
    }
}
