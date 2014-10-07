
import java.lang.Object;
import java.lang.String;
import java.lang.System;

import java.awt.Rectangle;

public class RectangleArithmetics extends Object {

	private static final int MARGIN_LENGTH = 1;
	private static final int SPACE_LENGTH = 8;

	public RectangleArithmetics() {
		super();
	}

	public static void sum(Rectangle a, Rectangle b) {

		Rectangle r = new Rectangle();

		System.out.println(">> SUM");
		System.out.printf("...A: %s\n", a);
		System.out.printf("...B: %s\n", b);
		System.out.printf("...R(NOT INITIALIZED): %s\n", r);

		r.setBounds(a);
		System.out.printf("...R(INITIALIZED): %s\n", r);

		r.add(b);
		System.out.printf("...R(A + B): %s\n", r);

	}

	public static void main(String[] args) {

		Rectangle[] rects = new Rectangle[9];
		int i, row, col;

		System.out.println(">> MAIN");

		for (i = 0; i < rects.length; ++i) {
			row = i / 3;
			col = i % 3;
			rects[i] = new Rectangle();
			rects[i].setBounds(
				(col + 1) * MARGIN_LENGTH + col * SPACE_LENGTH,
				(row + 1) * MARGIN_LENGTH + row * SPACE_LENGTH,
				SPACE_LENGTH,
				SPACE_LENGTH
			);
			System.out.printf("...#%d. %s\n", i, rects[i]);
		}

		sum(rects[8], rects[4]);
		sum(rects[2], rects[3]);
		sum(rects[3], rects[2]);
		sum(rects[8], rects[0]);

	}

}
