package com.noideaman.gui.menu;

import com.noideaman.cam.filters.FilterType;

import java.awt.MenuItem;

public class FilterTypeMenuItem extends MenuItem {
    private FilterType filterType;

    public FilterTypeMenuItem(String text) {
        super(text);
    }

    public void setFilterType(FilterType type) {
        this.filterType = type;
    }

    public FilterType getFilterType() {
        return this.filterType;
    }

}
