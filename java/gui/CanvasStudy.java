
import java.lang.Object;
import java.lang.String;
import java.lang.Math;
import java.lang.System;
import java.lang.Exception;

import java.awt.Window;
import java.awt.Frame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CanvasStudy extends Canvas implements KeyListener {

    /*
     * Private Members
     */

    private Color color;

    /*
     * Constructors
     */

    public CanvasStudy() {
        super();
        this.addKeyListener(this);
        this.generateRandomColor();
    }

    /*
     * Private Methods
     */

    private void generateRandomColor() {
        int r, g, b;
        r = (int)(Math.random() * 256.0);
        g = (int)(Math.random() * 256.0);
        b = (int)(Math.random() * 256.0);
        this.color = new Color(r, g, b);
    }

    /*
     * Public Methods
     */

    // Interface: WindowListener

    @Override
    public void keyPressed(KeyEvent e) {
        // nothing...
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing...
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.generateRandomColor();
        this.repaint();
    }

    // Graphics

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    // Canvas

    @Override
    public void paint(Graphics g) {
        System.out.println(">> PAINT");
        (new Exception()).printStackTrace();
        this.draw(g);
    }

    @Override
    public void update(Graphics g) {
        System.out.println(">> UPDATE");
        (new Exception()).printStackTrace();
        this.draw(g);
    }

    /*
     * Static Methods
     */

    public static void main(String[] args) {

        Frame frame = new Frame("Studying Canvas");
        CanvasStudy canvas = new CanvasStudy();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Window window = e.getWindow();
                window.setVisible(false);
                window.dispose();
            }
        });

        frame.add(canvas);
        frame.setSize(320, 320);
        frame.setVisible(true);

    }

}

