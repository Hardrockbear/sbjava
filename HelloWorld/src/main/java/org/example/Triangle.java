package org.example;

import static java.lang.Math.*;

//треугольник
public class Triangle implements Figure{
    private double a;
    private double b;
    private double c;
    private double angle;

    public Triangle(double a, double b, double angle) {
        if (a < 0 || b < 0 || angle < 0 || angle > 180) {
            throw new IllegalArgumentException("Параметры должны быть положительными и угол треугольника не может быть > 180");
        }

        this.a = a;
        this.b = b;
        this.angle = toRadians(angle);
        this.c = sqrt(a * a + b * b - 2 * a * b * cos(angle));
    }

    @Override
    public double P() {
        return a + b + c;
    }

    @Override
    public double Sq() {
        return 0.5 * a * b * sin(angle);
    }
}
