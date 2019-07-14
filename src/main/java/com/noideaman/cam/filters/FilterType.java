package com.noideaman.cam.filters;

import com.noideaman.cam.filters.color.*;
import com.noideaman.cam.filters.effects.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FilterType {
    BlackAndWhiteFilter("Black and White", 0, BlackWhiteFilter.class),
    BlueFilter("Blue", 0, com.noideaman.cam.filters.color.BlueFilter.class),
    GreenFilter("Green", 0, com.noideaman.cam.filters.color.GreenFilter.class),
    RedFilter("Red", 0, com.noideaman.cam.filters.color.RedFilter.class),
    NegativeFilter("Negative", 0, com.noideaman.cam.filters.color.NegativeFilter.class),
    SepiaFilter("Sepia", 0, com.noideaman.cam.filters.color.SepiaFilter.class),
    GrayScaleFilter("Gray Scale", 0, com.noideaman.cam.filters.color.GrayScaleFilter.class),
    ColorScreenEffect("Color Screen", 1, ColorScreenFilter.class),
    FunkyEffect("Funky", 1, FunkyFilter.class),
    GhostEffect("Ghosting", 1, GhostFilter.class),
    HorizontalMirrorEffect("Horizontal Mirror", 1, HorizontalMirror.class),
    SinusoidalEffect("Sinusoidal", 1, com.noideaman.cam.filters.effects.SinusoidalEffect.class);

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
