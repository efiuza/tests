
import java.lang.Object;
import java.lang.String;
import java.lang.System;

import java.util.HashMap;

import java.awt.Stroke;
import java.awt.BasicStroke;

public class CastingTest extends Object {

    private HashMap<String, Object> cache;

    public CastingTest() {
        super();
        this.cache = new HashMap<String, Object>();
        this.cache.put("key.stroke", null);
    }

    public Stroke getStroke() {
        Stroke stroke = (Stroke)this.cache.get("key.stroke");
        if (stroke == null) {
            System.out.println("Oops... Cache miss!");
            stroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
            this.cache.put("key.stroke", stroke);
        }
        return stroke;
    }

    public static void main(String[] args) {

        CastingTest test = new CastingTest();
        Stroke b, a = test.getStroke();

        for (int i = 1; i <= 10; ++i) {
            b = a;
            a = test.getStroke();
            System.out.printf(
                "TEST #%02d... %s\n", i,
                (a == b ? "PASSED! :)" : "FAILED!!! :(")
            );
        }

    }

}
