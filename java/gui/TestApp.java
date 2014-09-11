
import java.lang.Object;
import java.lang.String;

import java.awt.Frame;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class TestApp extends WindowAdapter {

    public TestApp() {
        super();
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        Frame frame = new Frame("TestApp");
        TestCanvas canvas = new TestCanvas();
        frame.addWindowListener(app);
        frame.setBounds(35, 35, 640, 480);
        frame.add(canvas);
        frame.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
    }

}
