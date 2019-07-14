package com.noideaman;

import com.noideaman.cam.filters.FilterType;

import java.awt.event.KeyEvent;
import java.util.Optional;

public class ApplicationState {
    public static class KeyBoardState {
        private KeyEvent keyEvent;
        KeyBoardState() { }

        public void setKeyEvent(KeyEvent event) {
            this.keyEvent = event;
        }

        public Optional<KeyEvent> getKeyEvent() {
            return Optional.ofNullable(this.keyEvent);
        }

        public void clearKeyBoardState() {
            this.keyEvent = null;
        }
    }

    public static class FilterTypeState {
        private FilterType filterType;

        public FilterType getFilterType() {
            return filterType;
        }

        public void setFilterType(FilterType filterType) {
            this.filterType = filterType;
        }

        public void clearFilterState() {
            this.filterType = null;
        }
    }

    public ApplicationState() {
        this.keyBoardState = new KeyBoardState();
        this.filterTypeState = new FilterTypeState();
    }

    private KeyBoardState keyBoardState;
    public void triggerKeyboardEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            this.keyBoardState.setKeyEvent(keyEvent);
        } else {
            this.keyBoardState.clearKeyBoardState();
        }
    }

    public KeyBoardState getKeyBoardState() {
        return this.keyBoardState;
    }

    private FilterTypeState filterTypeState;
    public void triggerFilterTypeEvent(FilterType filterType) {
        if (this.filterTypeState.getFilterType() == filterType) {
            this.filterTypeState.clearFilterState();
        } else {
            this.filterTypeState.setFilterType(filterType);
        }
    }

    public FilterTypeState getFilterTypeState() {
        return this.filterTypeState;
    }
}
