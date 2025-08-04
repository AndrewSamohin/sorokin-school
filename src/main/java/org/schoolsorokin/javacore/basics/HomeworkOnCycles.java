package org.schoolsorokin.javacore.basics;

public class HomeworkOnCycles {
    public void cycles() {
        //Выводим на экран числа от 1 до 100, которые делятся на 3
        for (int i = 0; i < 100; i++) {
            if (i % 3 != 0){
                continue;
            }
            System.out.print(i + ", ");
        }

        //Обратный отсчет от 10 с помощью цикла while
        int count = 10;
        while (count > 0) {
            System.out.println(count);
            count--;
        }

        //Выводим на экран таблицу умножения
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                System.out.println(i + " * " + j + " = " + (i * j) + "\t");
                //Для большей читаемости вывода
                if (j == 9) {
                    System.out.println();
                }
            }
        }

        //Цикл остановится, когда произведение станет больше 30
        for (int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                if (i * j > 30) {
                    break;
                }
                System.out.println(i + " * " + j + " = " + (i * j) + "\t");
            }
        }
    }
}
