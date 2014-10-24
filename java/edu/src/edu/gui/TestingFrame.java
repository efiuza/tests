package edu.gui;

import java.awt.Color;
import java.awt.Window;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public final class TestingFrame extends WindowAdapter {

    public TestingFrame() {
        super();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
    }

    public static void main(String[] args) {

        Frame frame;
        ShapeDrawingComponent component;

        // setup component
        component = new ShapeDrawingComponent();
        component.setBackground(Color.BLACK);
        component.setForeground(Color.GREEN);

        // setup frame
        frame = new Frame("Testing Frame");
        frame.addWindowListener(new TestingFrame());
        frame.setBounds(10, 10, 320, 480);
        frame.add(component, BorderLayout.CENTER);
        frame.setVisible(true);

    }

}
