package bspline;

import java.awt.Graphics;
import java.util.List;

abstract class BSpline {
	
	/** Ordnung der Spline-Kurve (= Grad + 1) */
	protected int k;

	/** Knotenvektor */
	protected double[] knotVector;

	/** Auflösung der Kurve, Schrittweite, Zeichengenauigkeit */
	private double h;
	
	/**
	 * Eine Liste von Point-Objekten Dies sind die Stütz- und Kontrollpunkte
	 */
	protected List<Point> points;

	

	/**
	 * B-Spline-Kurve 
	 * 
	 * @param points Kontrollpunkte
	 * @param k Grad der B-Spline + 1
	 * @param h Schrittweite beim Zeichnen der B-Spline
	 */
	BSpline(List<Point> points, int k, double h) {
		// TODO: Hier Ihr Code...
	}

	/**
	 * Zeichne eine B-Spline mit Stütz- und Kontrollpunkten aus points.
	 * 
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		// TODO: Ihr Code hier
	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * BSpline-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return 2D-Koordinate der B-Spline-Kurve für Parameter t
	 */
	Point bSpline(double t) {
		// TODO: Ihr Code hier
			return null;
	}

	/**
	 * Berechne den Wert der B-Spline-Basisfunktion N_{i,k}(t) analog zu Casteljau.
	 * 
	 * @param i Index der B-Spline-Basisfunktion N_{i,k}
	 * @param k Grad der B-Spline-Basisfunktion N_{i,k}
	 * @param t Parameter, an dem N_{i,k} ausgewertet wird
	 * @return N_{i,k}(t)
	 */
	double nik(int i, int k, double t) {
		// TODO: Ihr Code hier
	}
}
