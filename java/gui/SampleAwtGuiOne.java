import java.lang.String;
import java.lang.System;

import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class SampleAwtGuiOne extends WindowAdapter implements MouseMotionListener {

    public SampleAwtGuiOne() {
        super();
    }

    public void mouseMoved(MouseEvent e) {
        System.out.printf("Mouse Moved: %d, %d\n", e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        System.out.printf("Mouse Dragged: %d, %d\n", e.getX(), e.getY());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window is being closed...");
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("Window opened...");
    }

    public static void main(String[] args) {

        SampleAwtGuiOne delegate = new SampleAwtGuiOne();
        Frame frame = new Frame("My Sample AWT GUI One");
        frame.addMouseMotionListener(delegate);
        frame.addWindowListener(delegate);
        frame.setLayout(new FlowLayout());
        frame.setSize(256, 256);
        frame.add(new Button("Test Button"));
        frame.setVisible(true);

    }

}

