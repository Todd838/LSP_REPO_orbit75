package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {

        // Circle radius 3.0
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));

        // Rectangle 5.0 x 2.0
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));

        // Triangle base 10, height 6
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));

        // Square side 4
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Demonstrate exception handling
        try {
            AreaCalculator.area(-5); // negative side for square
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        /*
        Comment on overloading:
        Using method overloading allows us to use the same method name 'area' for different shapes,
        which is cleaner and more intuitive than naming methods differently (circleArea, rectangleArea, etc.).
        Overloading improves readability and keeps the API consistent.
        */
    }
}

