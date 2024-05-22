package bspline;

import java.util.List;

public class PeriodicUniformBSpline extends BSpline
{
    public PeriodicUniformBSpline(List<Point> points, int k, double h)
    {
        super(points, k, h);
        knotVector = new double[points.size() + k];
        for (int i = 0; i < points.size() + k; i++)
        {
            knotVector[i] = i;
        }
    }
}
