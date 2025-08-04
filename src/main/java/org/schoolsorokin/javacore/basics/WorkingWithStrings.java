package org.schoolsorokin.javacore.basics;

import java.util.Scanner;

public class WorkingWithStrings {
    public void strings() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        //Вывод длины строки
        System.out.println(line.length());

        //Вывод строки в верхнем регистре
        System.out.println(line.toUpperCase());

        //Проверка строки на наличие слова "Java"
        if (line.contains("Java")) {
            System.out.println(line.indexOf("Java"));
        }
        else {
            System.out.println("Не найдено.");
        }

        //Вывод слов
        String[] words = line.split(" ");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
