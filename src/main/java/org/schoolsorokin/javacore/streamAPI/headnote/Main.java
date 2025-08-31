package org.schoolsorokin.javacore.streamAPI.headnote;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyCalculator cal1 = new MyCalculator() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        };

        MyCalculator cal2 = ((a, b) -> a + b);

        testCalulator(cal1);
        testCalulator(cal2);
    }

    public static void testCalulator(MyCalculator calc) {
        double sum = calc.calculate(10,5);
        System.out.println(sum);
    }
}
