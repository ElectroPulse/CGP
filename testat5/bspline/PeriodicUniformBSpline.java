package bspline;

import java.util.List;

public class PeriodicUniformBSpline extends BSpline
{
    public PeriodicUniformBSpline(List<Point> points, int k, double h)
    {
        super(points, k, h);
    }
}
