package org.schoolsorokin.javacore.oop.shape;

public class Canvas {
    public static void main(String[] args) {
        Shape[]  shapes = {
                new  Circle( "Cirle", 45.9),
                new Square("Square", 24.9),
                new Triangle("Triangle", 11.5)
        };

        for (Shape shape : shapes) {
            shape.draw();
        }

        for (Shape shape : shapes) {
            shape.calculateArea();
        }
    }
}
