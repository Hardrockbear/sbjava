package org.example;

//прямоугольник
public class Rectangle implements Figure{
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Параметры фигуры должны быть > 0");
        }

        this.length = length;
        this.width = width;
    }

    @Override
    public double P() {
        return (length + width) * 2;
    }

    @Override
    public double Sq() {
        return length * width;
    }
}
