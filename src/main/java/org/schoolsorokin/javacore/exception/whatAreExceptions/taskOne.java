package org.schoolsorokin.javacore.exception.whatAreExceptions;

import java.util.Scanner;

public class taskOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Напишите два числа: ");
        int a = sc.nextInt();
        int b =  sc.nextInt();

        try {
            int c = a / b;
            System.out.println("Ответ: " + c);
        } catch (ArithmeticException e) {
            System.out.println("Делить на ноль нельзя! Попробуйте другое число");
        } finally {
            System.out.println("Спасибо за использование программы.3");
        }
    }
}
