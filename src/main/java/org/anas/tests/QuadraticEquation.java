package org.anas.tests;

public class QuadraticEquation {

    private static final double EPSILON = 1e-9;

    public double[] solve(double a, double b, double c) {
        if (anyNone(a, b, c) || anyInfinite(a, b, c)) {
            throw new IllegalArgumentException("Invalid coefficient");
        }
        
        if (Math.abs(a) < EPSILON) {
            throw new IllegalArgumentException("Coefficient 'a' must not be zero");
        }

        double determinant = b * b - 4 * a * c;
        if (determinant < -EPSILON) {
            return new double[]{};
        }
        if (Math.abs(determinant) <= EPSILON) {
            double x = -b / (2 * a);
            return new double[]{x, x};
        }

        double sqrtD = Math.sqrt(determinant);
        double x1 = (-b + sqrtD) / (2 * a);
        double x2 = (-b - sqrtD) / (2 * a);
        return new double[]{x1, x2};
    }

    private boolean anyInfinite(double a, double b, double c) {
        return Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c);
    }

    private boolean anyNone(double a, double b, double c) {
        return Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c);
    }
}