package bspline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BSplinePanel extends JPanel
{
    /**
     * Dimension des Zeichen-Panels
     */
    private int width = 500;
    private int height = 500;

    /**
     * Eine Liste von vier Point2D-Objekten. Dies sind die Stütz- und
     * Kontrollpunkte.
     */
    List<Point> points = new ArrayList<Point>();

    /**
     * wird, nachdem alle Kontrollpunkte gesetzt wurden, auf false gesetzt
     */
    boolean setPoints = true;
    /**
     * Falls !setPoints und Benutzer selektiert Stützpunkt
     */
    private boolean dragging = false;
    /**
     * Index des selektierten Stützpunkts
     */
    private int picked;

    /**
     * Ordnung der B-Spline
     */
    private int k = 3;

    /**
     * Konstruktor des Panels
     */
    public BSplinePanel()
    {
        setPreferredSize(new Dimension(width, height));

        /** Koordinaten eines Mausklicks abfragen */
        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
                if (setPoints)
                {
                    // Modus: Punkt setzen
                    int x = evt.getX();
                    int y = evt.getY();
                    // Falls einer der bereits gesetzten Punkte nochmals geklickt wird, Modus "Punkt
                    // setzen" beenden
                    for (int p = 0; p < points.size(); p++)
                    {
                        double dx = x - points.get(p).x;
                        double dy = y - points.get(p).y;
                        // Radius der Selektion: 6
                        if (dx * dx + dy * dy <= 36.0)
                        {
                            // Punkt nochmals geklickt
                            setPoints = false;
                            break;
                        }
                    }
                    // Noch im "Punkt setzen"-Modus. Punkt zur Liste hinzufügen.
                    if (setPoints)
                    {
                        points.add(new Point(x, y));
                    }
                }
                if (!setPoints)
                {
                    // Ermittle, ob Benutzer einen Stützpunkt selektiert
                    int x = evt.getX();
                    int y = evt.getY();
                    for (int p = 0; p < points.size(); p++)
                    {
                        double dx = x - points.get(p).x;
                        double dy = y - points.get(p).y;
                        // Radius der Selektion: 6
                        if (dx * dx + dy * dy <= 36.0)
                        {
                            dragging = true;
                            picked = p;
                        }
                    }
                }
            }

            // Selektion Stützpunkt freigeben.
            public void mouseReleased(MouseEvent evt)
            {
                if (dragging)
                {
                    dragging = false;
                    picked = -1;
                } else
                {
                    // Falls nicht "gedraggt"...
                    if (!setPoints)
                    {
                        // ... und keine Punkte gesetzt werden -> Grad der B-Spline erhöhen
                        k++;
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            // Selektierten Stützpunkt verschieben.
            public void mouseDragged(MouseEvent evt)
            {
                if (dragging)
                {
                    points.get(picked).x = evt.getX();
                    points.get(picked).y = evt.getY();
                }
            }
        });
    }

    /**
     * Inhalt des Zeichen-Panels.
     */
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        this.setBackground(Color.BLACK);
        Dimension d = this.getSize();
        width = d.width;
        height = d.height;

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.WHITE);

        for (int p = 0; p < points.size(); p++)
        {
            // Zeichne kleine Quadrate um gesetzte Punkte
            graphics.drawRect((int) points.get(p).x - 2, (int) points.get(p).y - 2, 4, 4);
            // Verbinde die Punkte
            if (p > 0)
            {
                graphics.drawLine((int) points.get(p - 1).x, (int) points.get(p - 1).y, (int) points.get(p).x,
                        (int) points.get(p).y);
            }
        }
        // Zeichnen der BSpline-Kurve
        if (!setPoints)
        {
            graphics.setColor(Color.RED);
            (new PeriodicUniformBSpline(points, k, 0.05)).render(graphics);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("BSpline-Kurven");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BSplinePanel bspline = new BSplinePanel();
        frame.add(bspline);
        frame.pack();
        frame.setVisible(true);
        while (true)
        {
            // Neuzeichnen anstoßen
            frame.repaint();
            try
            {
                Thread.sleep(100L);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
