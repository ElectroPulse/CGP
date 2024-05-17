package bezier;

import java.awt.*;
import java.util.List;

class Bezier
{
    List<Point> points;
    double h;

    /**
     * Berechnet Beziér-Kurven. Der Grad der Beziér-Kurve ist über die Zahl der
     * Kontrollpunkte festgelegt.
     *
     * @param points Kontrollpunkte.
     * @param h      Schrittweite beim Zeichnen der Beziér-Kurve
     */
    public Bezier(List<Point> points, double h)
    {
        this.points = points;
        this.h = h;
    }

    /**
     * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
     * Bézier-Kurve für einen gegebenen Parameterwert errechnet.
     *
     * @param t Kurvenparameter
     * @return Koordinate der Bézier-Kurve
     */
    Point casteljau(double t)
    {
        Point[][] P = new Point[points.size()][points.size()];
        for (int i = 0; i < points.size(); i++)
        {
            P[0][i] = points.get(i);
        }
        for (int k = 1; k < points.size(); k++)
        {
            for (int i = k; i < points.size(); i++)
            {
                P[k][i] = new Point((1 - t), P[k - 1][i - 1], t, P[k - 1][i]);
            }
        }
        return P[points.size() - 1][points.size() - 1];
    }

    /**
     * Zeichne eine Bezier-Kurve mit Stütz- und Kontrollpunkten aus points.
     *
     * @param graphics Grafikobjekt
     */
    void render(Graphics graphics)
    {
        for (double i = 0; i < 1; i+=h)
        {
            Point p1 = casteljau(i);
            Point p2 = casteljau(i+h);
            graphics.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
        }
    }
}
