package org.schoolsorokin.javacore.basics;

import java.util.Scanner;

public class AnalysisOfRatings {

    public void analysis() {
        Scanner sc = new Scanner(System.in);
        //Ввод оценок
        System.out.println("Напиши оценку №1 (от 1 до 10): ");
        int grade1 = sc.nextInt();
        System.out.println("Напиши оценку №2 (от 1 до 10): ");
        int grade2 = sc.nextInt();
        System.out.println("Напиши оценку №3 (от 1 до 10): ");
        int grade3 = sc.nextInt();

        //Проверка лежит ли оценка в диапозоне 0 - 10
        if ((grade1 <= 10 && grade1 >= 0) && (grade2 <= 10 && grade2 >= 0) && (grade3 <= 10 && grade3 >= 0)) {
            double averageScore = (grade1 + grade2 + grade3) / 3.0;
            System.out.println("Средний балл: " +  averageScore);

            boolean checkGrade = (grade1 == 10 && grade2 == 10 && grade3 == 10);
            //Проверка оценок
            if (checkGrade) {
                System.out.println("У студента все оценки максимальные!");
            }
            else if (grade1 <= 2 || grade2 <= 2 || grade3 <= 2) {
                System.out.println("Встречается очень низкая оценка!");
            }
            //Проверка среднего балла
            if (averageScore >= 5.0) {
                System.out.println("У студента удовлетворительная успеваемость");
            }
            else {
                System.out.println("У студента неудовлетворительная успеваемость");
            }
        }
        else  {
            System.out.println("Ошибка: некорректная оценка!");
        }
    }

}
