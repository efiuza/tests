
import java.lang.Object;
import java.lang.String;
import java.lang.Math;
import java.lang.System;
import java.lang.Thread;
import java.lang.Exception;
import java.lang.InterruptedException;

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

    private static int drawCount   = 0;
    private static int updateCount = 0;
    private static int paintCount  = 0;
    private static int colorCount  = 0;

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
        System.out.printf(">> GENERATE_RANDOM_COLOR #%04d\n", ++colorCount);
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
        System.out.println(">> KEY_RELEASED");
        this.generateRandomColor();
        this.repaint();
        System.out.println("...repaint scheduled.");
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            System.out.println("...sleep interrupted.");
            ex.printStackTrace();
        }
        System.out.println("...just woke up!");
    }

    // Graphics

    public void draw(Graphics g) {
        System.out.printf(">> DRAW #%04d, #%04d\n", ++drawCount, colorCount);
        g.setColor(this.color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    // Canvas

    @Override
    public void paint(Graphics g) {
        System.out.printf(">> PAINT #%04d\n", ++paintCount);
        System.out.printf("...Size: %s\n", this.getSize());
        System.out.printf("...Location: %s\n", this.getLocation());
        System.out.printf("...Clip: %s\n", g.getClip());
        System.out.printf("...Clip Bounds: %s\n", g.getClipBounds());
        // (new Exception()).printStackTrace();
        this.draw(g);
    }

    @Override
    public void update(Graphics g) {
        System.out.printf(">> UPDATE #%04d\n", ++updateCount);
        System.out.printf("...Size: %s\n", this.getSize());
        System.out.printf("...Location: %s\n", this.getLocation());
        System.out.printf("...Clip: %s\n", g.getClip());
        System.out.printf("...Clip Bounds: %s\n", g.getClipBounds());
        // (new Exception()).printStackTrace();
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

