package lines;

import java.awt.Graphics;

public class Lines {
	private Graphics graphics;

	/**
	 * Verschiedene Methoden zum Zeichnen von Linien
	 * 
	 * @param graphics Grafik-Kontext, in den gezeichnet wird
	 */
	public Lines(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * Methode zum Zeichnen eines Pixels.
	 * 
	 * HACK: Zeichne Pixel als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines einzelnen Pixels!
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	/**
	 * Konventionelle Linien-Berechnung über y = m*x + b
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawLineEquation(int x0, int y0, int x1, int y1) {
		double m = (y1 - y0) / (double) (x1 - x0);
		double b = y0 - m * x0;
		for (int x = x0; x <= x1; x++) {
			int y = (int) (m * x + b);
			setPixel(x, y);
		}
	}

	// Shift geeignet bis Fensterhöhe 8192
	private final static int SHIFT = 18;
	private final static int GAMMA = 1 << (SHIFT - 5);

	/**
	 * Linien-Berechnung über Digital Differential Analyzer (DDA)
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawDda(int x0, int y0, int x1, int y1) {
		int m = ((y1 - y0) << SHIFT) / (x1 - x0);
		int y = (y0 << SHIFT) + GAMMA;
		for (int x = x0; x <= x1; x++) {
			setPixel(x, y >> SHIFT);
			y = y + m;
		}
	}

	/**
	 * Linien-Berechnung über Bresenham
	 * 
	 * @param x0 x-Koordinate Startpunkt
	 * @param y0 y-Koordinate Startpunkt
	 * @param x1 x-Koordinate Endpunkt
	 * @param y1 y-Koordinate Endpunkt
	 */
	void drawBresenham(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0, dy = y1 - y0, d = 2*(dy - dx), incrE = 2*dy, incrNE = 2*(dy - dx);
		int x = x0, y = y0;
		setPixel(x0, y0);
		while (x < x1) {
			if (d < 0) {
				d = d + incrE;
				x++;
			} else {
				d = d + incrNE;
				x++;
				y++;
			}
			setPixel(x, y);
		}
	}
}
