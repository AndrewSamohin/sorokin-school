package org.schoolsorokin.javacore.oop.shape;

public class Shape {
    public void draw() {
        System.out.println("Drawing a generic shape.");
    }

    private double areaValue;
    private String name;

    public void calculateArea() {
        System.out.println("Area " + name + ": " +  areaValue);
    }

    public void setAreaValue(double areaValue) {
        this.areaValue = areaValue;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }

    public Circle(String name, double areaValue) {
        super.setName(name);
        super.setAreaValue(areaValue);
    }
}

class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square.");
    }

    public Square(String name, double areaValue) {
        super.setName(name);
        super.setAreaValue(areaValue);
    }
}

class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle.");
    }

    public Triangle(String name, double areaValue) {
        super.setName(name);
        super.setAreaValue(areaValue);
    }
}
