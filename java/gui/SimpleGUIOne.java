
import java.lang.Object;
import java.lang.String;

import javax.swing.JFrame;
import javax.swing.JButton;

public class SimpleGUIOne extends Object {

    public SimpleGUIOne() {
        super();
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me!");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        (frame.getContentPane()).add(button);

        frame.setSize(256, 256);
        frame.setVisible(true);

    }

}
