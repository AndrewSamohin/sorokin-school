package org.schoolsorokin.javacore.basics;

import java.util.Scanner;

public class MovieSelection {

    public void movie() {
        Scanner input = new Scanner(System.in);

        System.out.println("Введите число от 1 до 5: ");
        int choice =  input.nextInt();

        if(choice >= 1 || choice <= 5) {
            switch (choice) {
                case 1:
                    System.out.println("Фильмы в жанре комедия: Солдаты неудачи, Большой Лебовски");
                    break;
                case 2:
                    System.out.println("Фильмы в жанре драма: Паразиты, Зеленая миля");
                    break;
                case 3:
                    System.out.println("Фильмы в жанре фантастика: Логан, Бегущий по лезвию");
                    break;
                case 4:
                    System.out.println("Фильмы в жанре боевик: Переводчик, Пчеловод");
                    break;
                case 5:
                    System.out.println("Фильмы в жанре аниме: Ходячий замок, Унесенные призраками");
                    break;
            }
        }
        else {
            System.out.println("Такого жанра не знаю.");
        }
    }

}
