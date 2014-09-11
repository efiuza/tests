
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class TestCanvas extends Canvas {

    private BasicStroke stroke;
    private Color color;

    public TestCanvas() {
        super();
        this.color = new Color(0x7F000000, true);
        this.stroke = new BasicStroke(
            8.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER
        );
    }

    private void render(Graphics2D g) {

        Dimension d = this.getSize();
        int marginX, marginY;

        marginX = (int)(d.width * 0.10f);
        marginY = (int)(d.height * 0.10f);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(this.color);
        g.setStroke(this.stroke);
        g.drawLine(
            marginX,
            marginY,
            d.width - marginX,
            d.height - marginY
        );

    }

    @Override
    public void paint(Graphics g) {
        if (g instanceof Graphics2D) {
            this.render((Graphics2D)g);
        }
    }

}
