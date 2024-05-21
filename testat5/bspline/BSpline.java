package bspline;

import java.awt.*;
import java.util.List;

abstract class BSpline
{

    /**
     * Ordnung der Spline-Kurve (= Grad + 1)
     */
    protected int k;

    /**
     * Knotenvektor
     */
    protected double[] knotVector;

    /**
     * Auflösung der Kurve, Schrittweite, Zeichengenauigkeit
     */
    private double h;

    /**
     * Eine Liste von Point-Objekten Dies sind die Stütz- und Kontrollpunkte
     */
    protected List<Point> points;


    /**
     * B-Spline-Kurve
     *
     * @param points Kontrollpunkte
     * @param k      Grad der B-Spline + 1
     * @param h      Schrittweite beim Zeichnen der B-Spline
     */
    public BSpline(List<Point> points, int k, double h)
    {
        this.points = points;
        this.k = k;
        this.h = h;
    }

    /**
     * Zeichne eine B-Spline mit Stütz- und Kontrollpunkten aus points.
     *
     * @param graphics Grafikobjekt
     */
    void render(Graphics graphics)
    {
        // TODO: Ihr Code hier
    }

    /**
     * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
     * BSpline-Kurve für einen gegebenen Parameterwert errechnet.
     *
     * @param t Kurvenparameter
     * @return 2D-Koordinate der B-Spline-Kurve für Parameter t
     */
    Point bSpline(double t)
    {
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
    double nik(int i, int k, double t)
    {
        double nik[][] = new double[k + 1][k + 1];
        for (int step = 0; step < k; step++)
        {
            nik[i + step][1] = (knotVector[i + step] <= t && t < knotVector[i + step + 1]) ? 1 : 0;
        }
        for (int k_step = 2; k_step <= k; k_step++)
        {
            for (int step = 0; step <= k - k_step; step++)
            {

                int i_step = i + step;
                double a = safeDiv((t - knotVector[i_step]), (knotVector[i_step + k_step - 1] - knotVector[i_step]));
                double b = safeDiv((knotVector[i_step + k_step] - t), (knotVector[i_step + k_step] - knotVector[i_step + 1]));
                nik[i_step][k_step] = a * nik[i_step][k_step - 1] + b * nik[i_step + 1][k_step - 1];
            }
        }
        return nik[i][k];
    }

    private double safeDiv(double top, double btm)
    {
        if (btm == 0)
        {
            return 0;
        }
        return top / btm;
    }
}
