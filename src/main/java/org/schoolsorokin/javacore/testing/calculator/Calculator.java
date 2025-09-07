package org.schoolsorokin.javacore.testing.calculator;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.multiply(2, 5));
        System.out.println(calculator.sum(3,4));
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}
