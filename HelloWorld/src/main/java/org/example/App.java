package org.example;

import java.util.Scanner;

/**
 * Считаем параметры фигур
 *
 */
public class App 
{
    public static void main(String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Figure figure;

        System.out.println("Выберите фигуру: 1 - прямоугольник, 2 - квадрат, 3 - треугольник");

        String input = scanner.nextLine();

        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("Введите длину:");
                double length = scanner.nextDouble();

                System.out.println("Введите ширину:");
                double width = scanner.nextDouble();

                figure = new Rectangle(length, width);
                break;
            case 2:
                System.out.println("Введите сторону:");
                double side = scanner.nextDouble();

                figure = new Square(side);
                break;
            case 3:
                System.out.println("Введите первую сторону:");
                double a = scanner.nextDouble();

                System.out.println("Введите вторую сторону:");
                double b = scanner.nextDouble();

                System.out.println("Введите угол в градусах:");
                double angle = scanner.nextDouble();

                figure = new Triangle(a, b, angle);
                break;
            default:
                throw new RuntimeException("Некорректный выбор");
        }

        System.out.println("Площадь фигуры = " + figure.Sq() + ". Периметр фигуры = " + figure.P());
    }
}
