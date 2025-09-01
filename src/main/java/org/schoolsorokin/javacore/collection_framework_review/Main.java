package org.schoolsorokin.javacore.collection_framework_review;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        boolean flag = true;

        while (flag) {
            outputMenu();
            System.out.println("Выберите действие: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    //Добавить контакт
                    System.out.println("Введите имя: ");
                    String name = sc.nextLine();
                    System.out.println("Введите телефон: ");
                    String phone = sc.nextLine();
                    System.out.println("Введите email: ");
                    String email = sc.nextLine();
                    System.out.println("Введите группу: ");
                    String group = sc.nextLine();

                    Contact newContact = new Contact(name, phone, email, group);
                    System.out.println(manager.addContact(newContact) ? "Контакт добавлен." : "Такой контакт уже есть.");
                    break;

                case "2":
                    //Удаление контакта
                    System.out.println("Введите имя для удаления контакта: ");
                    String removeName = sc.nextLine();
                    List<Contact> contactsToRemove = manager.findContact(removeName);

                    //Поиска контакта
                    if (contactsToRemove.isEmpty()) {
                        System.out.println("Контакт не найден.");
                    } else {
                        for (Contact contact : contactsToRemove) {
                            manager.removeContact(contact);
                            System.out.println("Контакт " + contact + " удален.");
                        }
                    }
                    break;

                case "3":
                    //Вывод всех контактов
                    manager.showAllContacts();
                    break;

                case "4":
                    //Поиск по имени
                    System.out.println("Введите имя для поиска: ");
                    String searchName = sc.nextLine();
                    List<Contact> results = manager.findContact(searchName);

                    if (results.isEmpty()) {
                        System.out.println("Контакт не найден");
                    } else {
                        for (Contact contact : results) {
                            System.out.println(contact);
                        }
                    }
                    break;

                case "5":
                    //Просмотр контактов по группе
                    System.out.println("Введите название группы: ");
                    String searchGroup = sc.nextLine();
                    manager.showContactsByGroup(searchGroup);
                    break;

                case "0":
                    flag = false;
                    System.out.println("Выход из программы.");
                    break;
            }
        }
    }

    //Вывод основного меню
    public static void outputMenu() {
        System.out.println(
                "1: Добавить контакт. " +
                        "\n2: Удалить контакт. " +
                        "\n3: Посмотреть все контакты. " +
                        "\n4: Найти контакт. " +
                        "\n5: Посмотреть контакты по группе. " +
                        "\n0: Выход.");
    }
}