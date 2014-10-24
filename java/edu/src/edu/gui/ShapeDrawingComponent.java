package edu.gui;

import java.awt.Shape;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

final class ShapeDrawingComponent extends Component {

    public ShapeDrawingComponent() {
        super();
    }

    private Path2D buildPath() {
        Path2D.Float path = new Path2D.Float(Path2D.WIND_EVEN_ODD, 7);
        path.moveTo(0.5f, 0.5f);
        path.lineTo(1.0f, 0.0f);
        path.lineTo(3.0f, 0.0f);
        path.lineTo(3.5f, 0.5f);
        path.lineTo(3.0f, 1.0f);
        path.lineTo(1.0f, 1.0f);
        path.closePath();
        return path;
    }

    private Shape getShapePath(float x, float y, float width, float height) {
        //return (new Ellipse2D.Float(x, y, width, height));
        AffineTransform transform;
        Path2D path = this.buildPath();
        Rectangle2D.Float bounds = new Rectangle2D.Float();
        float scale, sRatio, bRatio;
        bounds.setRect(path.getBounds2D());
        sRatio = bounds.height / bounds.width;
        bRatio = height / width;
        if (bRatio > sRatio) {
            scale = width / bounds.width;
        } else {
            scale = height / bounds.height;
        }
        transform = AffineTransform.getScaleInstance((double)scale, (double)scale);
        transform.translate(1.0, 1.0);
        path.transform(transform);
        return path;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2;
        int width, height;

        width = this.getWidth();
        height = this.getHeight();

        if (width > 0 && height > 0) {
            g2 = (Graphics2D)g;
            g2.clearRect(0, 0, width, height);
            g2.fill(this.getShapePath(1.0f, 1.0f, width - 2.0f, height - 2.0f));
        }

    }

}
