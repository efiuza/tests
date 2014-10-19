
import java.lang.Object;
import java.lang.String;
import java.lang.System;
import java.awt.Rectangle;

public class RectangleAdditionTest extends Object {

    public static void main(String[] args) {

        Rectangle a, b, c;

        a = new Rectangle(0, 0, 10, 10);
        b = new Rectangle(10, 10, 15, 15);
        c = new Rectangle(45, 15, 10, 10);
        System.out.printf("a: %s\nb: %s\nc: %s\n", a, b, c);

        a.add(b);
        System.out.printf("> a + b: %s\n", a);

        a.add(c);
        System.out.printf("> a + b + c: %s\n\n", a);

    }

}
