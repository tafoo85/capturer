package com.noideaman.cam.filters;

import com.noideaman.ApplicationState;

import java.util.HashMap;
import java.util.Map;

public class FilterFactory {
    private final static Map<FilterType, IFilter> filterCache = new HashMap<>();

    static {
        filterCache.put(null, new IFilter() { });
        for (FilterType types : FilterType.values()) {
            try {
                filterCache.put(types, (IFilter)types.getFilterClass().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static IFilter getFilter(ApplicationState state) {
        ApplicationState.FilterTypeState filterTypeState = state.getFilterTypeState();
        FilterType filterType = filterTypeState.getFilterType();
        return filterCache.get(filterType);
    }
}
