package org.schoolsorokin.javacore.exception.processing;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        String[] history = new String[100];
        int historyIndex = 0;

        while (flag) {
            double a = 0;
            double b = 0;

            try {
                System.out.println("Введите первое число: ");
                a =  sc.nextInt();
                System.out.println("Введите второе число: ");
                b = sc.nextInt();
                double result = divide(a, b);
                System.out.println("Результат деления: " + result);

                history[historyIndex++] = "Деление " + a + " / " +  b + " = " + result;
            }
            catch (InputMismatchException e) {
                logger.log(Level.SEVERE, "Ошибка: Вы ввели неккореткное число!");
                history[historyIndex++] = "Ошибка: Некорректный ввод числа";
            }
            catch (ArithmeticException e) {
                logger.log(Level.SEVERE, "Ошибка: Деление на ноль!");
                history[historyIndex++] = "Деление " + a + " / " + b + " завершилось ошибкой: деление на ноль";
            }

            System.out.println("Хотите выполнить ещё одно деление? (да/нет): ");
            String choice = sc.next();

            if (choice.equals("нет")) {
                flag = false;
            }
        }

        System.out.println("История операций: ");
        for (int i = 0; i < historyIndex; i++) {
            System.out.println(history[i]);
        }

        System.out.println("\nСпасибо за использование программы!");
    }

    private static double divide(double a, double b) throws ArithmeticException {
        if (b == 0 || a == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return a / b;
    }
}
