package com.noideaman.cam.filters;

import com.noideaman.cam.filters.color.*;
import com.noideaman.cam.filters.effects.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FilterType {
    BlackAndWhiteFilter("Black and White", FilterType.FILTER_TYPE, BlackWhiteFilter.class),
    BlueFilter("Blue", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.BlueFilter.class),
    GreenFilter("Green", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.GreenFilter.class),
    RedFilter("Red", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.RedFilter.class),
    NegativeFilter("Negative", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.NegativeFilter.class),
    SepiaFilter("Sepia", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.SepiaFilter.class),
    GrayScaleFilter("Gray Scale", FilterType.FILTER_TYPE, com.noideaman.cam.filters.color.GrayScaleFilter.class),
    ColorScreenEffect("Color Screen", FilterType.EFFECT_TYPE, ColorScreenFilter.class),
    FunkyEffect("Funky", FilterType.EFFECT_TYPE, FunkyFilter.class),
    GhostEffect("Ghosting", FilterType.EFFECT_TYPE, GhostFilter.class),
    GuassianBlurEffect("Low Pass", FilterType.EFFECT_TYPE, BlurFilter.class),
    HorizontalMirrorEffect("Horizontal Mirror", FilterType.EFFECT_TYPE, HorizontalMirror.class),
    SinusoidalEffect("Trippy", FilterType.EFFECT_TYPE, TrippyEffect.class);

    private String displayName;
    private int displayType;
    private Class filterClass;

    public final static int FILTER_TYPE = 0;
    public final static int EFFECT_TYPE = 1;

    FilterType(String displayName, int displayType, Class filterClass) {
        this.displayName = displayName;
        this.displayType = displayType;
        this.filterClass = filterClass;
    }

    public static List<FilterType> getFiltersByType(int type) {
        return Arrays.asList(FilterType.values())
                .stream()
                .filter(filterType -> filterType.displayType == type)
                .collect(Collectors.toList());
    }

    public Class getFilterClass() {
        return this.filterClass;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public int getDisplayType() {
        return this.displayType;
    }
}
