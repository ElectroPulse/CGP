import java.awt.Graphics;
import java.util.List;

class Bezier {
	// TODO: Definieren Sie benötigte Attribute hier.

	/**
	 * Berechnet Beziér-Kurven. Der Grad der Beziér-Kurve ist über die Zahl der
	 * Kontrollpunkte festgelegt.
	 * 
	 * @param points Kontrollpunkte.
	 * @param h      Schrittweite beim Zeichnen der Beziér-Kurve
	 */
	Bezier(List<Point> points, double h) {
		// TODO: Hier Ihr Code...
	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * Bézier-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return Koordinate der Bézier-Kurve
	 */
	Point casteljau(double t) {
		// TODO: Hier Ihr Code...

	}

	/**
	 * Zeichne eine Bezier-Kurve mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		// TODO: Hier Ihr Code...
	}
}
