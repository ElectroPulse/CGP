package bspline;

import java.util.List;

public class NonPeriodicUniformBSpline extends BSpline
{
    public NonPeriodicUniformBSpline(List<Point> points, int k, double h)
    {
        super(points, k, h);
    }
}
