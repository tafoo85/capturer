package com.noideaman.cam.filters.effects;

import com.noideaman.cam.filters.IFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.awt.image.BufferedImage;

import static org.bytedeco.opencv.global.opencv_imgproc.blur;

public class BlurFilter implements IFilter {
    public Frame filter(Frame capturedFrame) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        Mat destinationMatrix = new Mat();
        Mat filteredImage = converter.convert(capturedFrame);
        blur(filteredImage, destinationMatrix, new Size(5, 5));

        return converter.convert(destinationMatrix);
    }
}
