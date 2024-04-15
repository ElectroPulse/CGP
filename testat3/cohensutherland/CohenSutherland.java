package cohensutherland;

import java.awt.*;

/**
 * Clipping nach Cohen-Sutherland.
 */
public class CohenSutherland {
	/** Zum Zeichnen */
	private Graphics graphics;

	/** Dimension des Clipping-Rechtecks */
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	/**
	 * Ctor.
	 * 
	 * @param graphics Zum Zeichnen
	 * @param xmin     minimale x-Koordinate
	 * @param ymin     minimale y-Koordinate
	 * @param xmax     maximale x-Koordinate
	 * @param ymax     maximale y-Koordinate
	 */
	public CohenSutherland(Graphics graphics, int xmin, int ymin, int xmax, int ymax) {
		super();
		this.graphics = graphics;
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	/**
	 * Berechne den Cohen-Sutherland-Outputcode für einen Punkt.
	 * 
	 * @formatter:off
	 * viertletztes Bit = 1 <=> y > ymax 
	 * drittletztes Bit = 1 <=> y < ymin
	 * vorletztes Bit = 1 <=> x > xmax 
	 * letztes Bit = 1 <=> x < xmin
	 * @formatter:on
	 * 
	 * Die 4 Bits werden sehr verschwenderisch in einem int untergebracht.
	 * 
	 * Warum kein byte? Die bitweisen Operationen sind für Datentyp byte nicht
	 * definiert! Genauer werden z.B. die Bytes bei byte1 | byte2 zu ints gecastet
	 * und das Ergebnis ist ein int.
	 * Mehr Details: <a href="https://stackoverflow.com/questions/27582233/why-byte-and-short-values-are-promoted-to-int-when-an-expression-is-evaluated">Stack Overflow</a>
	 * 
	 * @param x x-Koordinate Punkt
	 * @param y y-Koordinate Punkt
	 * @return Outputcode
	 */
	int outputCode(int x, int y) {
		int code = 0;
		if (y > ymax) {
			code |= Area.GTYMAX;
		}
		else if (y < ymin) {
			code |= Area.LTYMIN;
		}
		if (x > xmax) {
			code |= Area.GTXMAX;
		}
		else if (x < xmin) {
			code |= Area.LTXMIN;
		}
		return code;
	}

	/**
	 * Clipping nach Cohen-Sutherland. Die Linie von (xA,yA) nach (xE,yE) wird an
	 * dem durch die Attribute (xmin,ymin) und (xmax,ymax) definierten Rechteck
	 * geclippt und der sichtbare Teil der Linie gezeichnet.
	 * 
	 * @param xA x-Koordinate Anfangspunkt Linie
	 * @param yA y-Koordinate Anfangspunkt Linie
	 * @param xE x-Koordinate Endpunkt Linie
	 * @param yE y-Koordinate Endpunkt Linie
	 */
	void clipLine(int xA, int yA, int xE, int yE) {

		if (outputCode(xA, yA) == 0 && outputCode(xE, yE) == 0) {
			// Linie komplett sichtbar
			drawGreen(xA, yA, xE, yE);
			return;
		}

		if ((outputCode(xA, yA) & outputCode(xE, yE)) != 0) {
			// Linie komplett unsichtbar
			drawGrey(xA, yA, xE, yE);
			return;
		}

		// Linie teilweise sichtbar
		// (xA,yA) ist oberhalb des Clipping-Rechtecks ymax
		if (Area.GTYMAX == (outputCode(xA,yA) & Area.GTYMAX)) {
			// Schnittpunkt mit ymax berechnen
			int x1 = (xE - xA) / (yE - yA) * (ymax - yE) + xE; // (x,y) = (x1,ymax)
			int y1 = ymax;
		}

		// (xA,yA) ist unterhalb des Clipping-Rechtecks ymin
		if (Area.LTYMIN == (outputCode(xA,yA) & Area.LTYMIN)) {
			// Schnittpunkt mit ymin berechnen
			int x2 = (xE - xA) / (yE - yA) * (ymin - yE) + xE; // (x,y) = (x2,ymin)
			int y2 = ymin;
		}

		// (xA,yA) ist rechts des Clipping-Rechtecks xmax
		if (Area.GTXMAX == (outputCode(xA,yA) & Area.GTXMAX)) {
			// Schnittpunkt mit xmax berechnen
			int x3 = xmax;
			int y3 = (yE - yA) / (xE - xA) * (xmax - xE) + yE; // (x,y) = (xmax,y1)
		}

		// (xA,yA) ist links des Clipping-Rechtecks xmin
		if (Area.LTXMIN == (outputCode(xA,yA) & Area.LTXMIN)) {
			// Schnittpunkt mit xmin berechnen
			int x4 = xmin;
			int y4 = (yE - yA) / (xE - xA) * (xmin - xE) + yE; // (x,y) = (xmin,y2)
		}

		graphics.setColor(Color.BLUE);
		graphics.drawLine(xA, yA, xE, yE);
	}

	private void drawGrey(int xA, int yA, int xE, int yE) {
		graphics.setColor(Color.GRAY);
		graphics.drawLine(xA, yA, xE, yE);
	}

	private void drawGreen(int xA, int yA, int xE, int yE) {
		graphics.setColor(Color.GREEN);
		graphics.drawLine(xA, yA, xE, yE);
	}
}
