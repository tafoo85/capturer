package com.noideaman.gui;

import com.noideaman.ApplicationState;
import com.noideaman.cam.Capturer;
import com.noideaman.cam.filters.FilterType;
import com.noideaman.gui.menu.FilterTypeMenuItem;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class Application {

    private final static ApplicationState state = new ApplicationState();

    private static class FrameSynchronizer {
        private Frame capturedFrame;
        synchronized Frame getCurrentFrame() {
            return this.capturedFrame;
        }

        synchronized void setCurrentFrame(Frame capturedFrame) {
            this.capturedFrame = capturedFrame;
        }
    }

    public static class CaptureThread implements Runnable {
        private Capturer capturer;
        private FrameSynchronizer synchronizer;

        CaptureThread(Capturer capturer, FrameSynchronizer synchronizer) {
            this.capturer = capturer;
            this.synchronizer = synchronizer;
        }
        @Override
        public void run() {
            while (true) {
                this.synchronizer.setCurrentFrame(capturer.captureFrame());
            }
        }
    }

    public static void main(String[] args) {
        FrameSynchronizer synchronizer = new FrameSynchronizer();
        Capturer capturer = new Capturer(state);
        capturer.init();

        CanvasFrame canvas = createCanvasFrame(capturer, state);

        Thread captureThread = new Thread(new CaptureThread(capturer, synchronizer));

        captureThread.start();

        while (true) {
            Frame currentFrame = synchronizer.getCurrentFrame();

            if (currentFrame != null) {
                canvas.showImage(currentFrame);
            }
        }
    }

    private static CanvasFrame createCanvasFrame(Capturer capturer, ApplicationState state) {
        CanvasFrame canvas = new CanvasFrame("WebCam Input", CanvasFrame.getDefaultGamma() / capturer.getGamma());

        initCanvasFrame(canvas);
        initComponents(canvas);
        initListeners(canvas);

        return canvas;
    }

    private static void initCanvasFrame(CanvasFrame canvas) {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    private static void initComponents(CanvasFrame canvas) {
        PopupMenu menu = createPopupMenu();


        canvas.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        canvas.getCanvas().add(menu);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                state.triggerKeyboardEvent(e);
            }
        });
    }

    private static PopupMenu createPopupMenu() {
        PopupMenu menu =  new PopupMenu();
        Menu filterMenu = new Menu("Filters");
        Menu effectsMenu = new Menu("Effects");

        Consumer<FilterType> menuCreator = (filterType) -> {
            FilterTypeMenuItem menuItem = new FilterTypeMenuItem(filterType.getDisplayName());
            menuItem.setFilterType(filterType);
            menuItem.addActionListener(event ->
                    state.triggerFilterTypeEvent(menuItem.getFilterType()));
            Menu toAddTo = filterType.getDisplayType() == FilterType.FILTER_TYPE ? filterMenu : effectsMenu;
            toAddTo.add(menuItem);
        };

        FilterType.getFiltersByType(FilterType.FILTER_TYPE).forEach(menuCreator);

        FilterType.getFiltersByType(FilterType.EFFECT_TYPE).forEach(menuCreator);

        menu.add(filterMenu);
        menu.add(effectsMenu);

        return menu;
    }

    private static void initListeners(CanvasFrame canvas) {

    }
}
