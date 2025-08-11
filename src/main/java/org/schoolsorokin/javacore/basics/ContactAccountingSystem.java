package org.schoolsorokin.javacore.basics;

import java.util.Scanner;

public class ContactAccountingSystem {
    private int menuItem;
    private int index = 0;

    String[] names = new String[100];
    String[] phoneNumbers = new String[100];
    Scanner sc = new Scanner(System.in);

    //Основной метод с циклом
    public void accountSystem() {
        do {
            //Вывод меню
            startMenu();
            System.out.println("Выберите пункт в меню: ");
            menuItem = sc.nextInt();
            sc.nextLine();
            //Проверка на корректный ввод меню
            if (menuItem < 5 &&  menuItem > 0) {
                menuImplementation();
            } else  {
                System.out.println("Неизвестный пункт меню.");
            }
        } while (menuItem != 5);
    }

    //Метод с реализацией пунктов в меню
    private void menuImplementation() {
        switch (menuItem) {

            //Добавления нового контакта
            case 1:
                addContact();
                break;

            //Вывод всех контактов
            case 2:
                displayAllContacts();
                break;

            //Поиск контакта по имени
            case 3:
                lookForContact();
                break;

            //Удаление контакта по имени
            case 4:
                deleteContact();
                break;

            //Выход из программы
            case 5:
                break;

            default:
                break;
        }
    }

    //Добавление нового контакта
    private void addContact() {
        System.out.println("Напишите имя: ");
        String name = sc.nextLine();

        if (!name.isBlank()) {
            System.out.println("Напишите телефон: ");
            String phoneNumber = sc.nextLine();
            if (phoneNumber.isBlank()) {
                System.out.println("Номер не может быть пустым.");
                return;
            }
            names[index] = name;
            phoneNumbers[index] = phoneNumber;
            index++;
        } else {
            System.out.println("Имя не может быть пустым");
        }
    }

    //Вывод всех контактов
    private void displayAllContacts() {
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null &&  phoneNumbers[i] != null) {
                System.out.println((i + 1)
                        + ". " + names[i]
                        + " - " + phoneNumbers[i]);
            }
        }
        System.out.println("Контакты закончились.");
    }

    //Поиск контакта по имени
    private void lookForContact() {
        System.out.println("Имя для поиска: ");
        String enteredName = sc.nextLine();

        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(enteredName)) {
                System.out.println("Номер телефона " + enteredName + ": " + phoneNumbers[i]);
                return;
            }
        }
        System.out.println("Контакт с именем " + enteredName + " не найден.");
    }

    //Удаление контакта
    private void deleteContact() {
        System.out.println("Введите имя для удаления контакта: ");
        String nameToDelete = sc.nextLine();
        boolean flag = false;

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
    }

    //Метод с описанием меню
    private void startMenu() {
        System.out.println("1. Добавить контакт");
        System.out.println("2. Просмотреть контакты");
        System.out.println("3. Найти контакт");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выйти");
    }
}