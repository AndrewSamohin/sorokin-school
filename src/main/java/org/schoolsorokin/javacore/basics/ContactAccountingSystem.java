package org.schoolsorokin.javacore.basics;

import java.util.Scanner;

public class ContactAccountingSystem {
    int menuItem;
    int index = 0;
    int i = 0;
    boolean flag = false;

    String[] names = new String[100];
    String[] phoneNumbers = new String[100];
    Scanner sc = new Scanner(System.in);

    //Основной метод с циклом
    public void accountSystem() {
        do {
            textMenu();
            System.out.println("Выберите пункт в меню: ");
            menuItem = sc.nextInt();
            sc.nextLine();
            menuImplementation();

        } while (menuItem != 5);
    }

    //Метод с реализацией пунктов в меню
    public void menuImplementation() {
        switch (menuItem) {

            //Добавления нового контакта
            case 1:
                System.out.println("Напишите свое Имя: ");
                String name = sc.nextLine();

                if (!name.isBlank()) {
                    System.out.println("Напишите свой телефон: ");
                    String phoneNumber = sc.nextLine();

                    names[index] = name;
                    phoneNumbers[index] = phoneNumber;
                    index++;
                } else {
                    System.out.println("Имя не может быть пустым");
                }
                break;

            //Вывод всех контактов
            case 2:
                int a = 1;

                for (i = 0; i < names.length; i++, a++) {
                    if (names[i] != null &&  phoneNumbers[i] != null) {
                        System.out.println(a + ". " + names[i] + " - " + phoneNumbers[i]);
                        flag = true;
                    }
                }
                if (!flag) {
                    System.out.println("Контакты закончились.");
                }

                break;

            //Поиск контакта по имени
            case 3:
                System.out.println("Имя для поиска: ");
                String enteredName = sc.nextLine();

                for (i = 0; i < names.length; i++) {
                    if (names[i] != null && names[i].equalsIgnoreCase(enteredName)) {
                        System.out.println("Номер телефона " + enteredName + ": " + phoneNumbers[i]);
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    System.out.println("Контакт с именем " + enteredName + " не найден.");
                }
                break;

            //Удаление контакта по имени
            case 4:
                System.out.println("Введите имя для удаления контакта: ");
                String nameToDelete = sc.nextLine();

                for (int i = 0; i < index; i++) {
                    if (names[i].equalsIgnoreCase(nameToDelete)) {
                        for (int j = i; j <  index - 1; j++) {
                            names[j] = names[j + 1];
                            phoneNumbers[j] = phoneNumbers[j + 1];
                        }
                        names[index - 1] = null;
                        phoneNumbers[index - 1] = null;
                        index--;
                        flag = true;
                        System.out.println("Контакт " + nameToDelete + " успешно удален.");
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Контакт с именем " + nameToDelete + " не найден.");
                }
                break;

            //Выход из программы
            case 5:
                break;

            default:
                System.out.println("Неизвестный пункт меню.");
                break;
        }
    }

    //Метод с описанием меню
    public void textMenu() {
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть контакты");
        System.out.println("3. Найти контакт");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выйти");
    }
}