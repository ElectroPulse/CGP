package bspline;

import java.util.List;

public class NonPeriodicUniformBSpline extends BSpline
{
    public NonPeriodicUniformBSpline(List<Point> points, int k, double h)
    {
        super(points, k, h);
        int step = 1;
        knotVector = new double[points.size() + k];
        for (int i = k; i < knotVector.length - k; i++)
        {
            knotVector[i] = step;
            step++;
        }
        for (int i = knotVector.length - k; i < knotVector.length; i++)
        {
            knotVector[i] = step;
        }
    }
}
