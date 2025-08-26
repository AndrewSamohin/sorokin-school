package org.schoolsorokin.javacore.exception.review;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Library lib = new  Library();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean flag = true;

        while (flag) {
            displayMenu(); //Вывод меню
            int choice = getAChoice();

            switch (choice) {
                case 1:
                    outputCatalog();
                    break;
                case 2:
                    addABook();
                    break;
                case 3:
                    issueABook();
                    break;
                case 4:
                    returnTheBook();
                    break;
                case 5:
                    System.out.println("Выходи из приложения.");
                    flag = false;
                    break;
                default:
                    System.out.println("Неверный пункт меню! Попробуйте снова.");
            }
        }
    }
    //Получение выбора пользователя с проверкой
    private static int getAChoice() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число (1-5): ");
            }
        }
    }
    //Вывод каталога
    private static void outputCatalog() {
        List<Book> books = lib.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Каталог пуст.");
        }
        else {
            System.out.println("\nКаталог: ");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    //Добавление книги
    private static void addABook() {
        try {
            System.out.println("Введите название книги: ");
            String title = sc.nextLine();

            System.out.println("Введите автора: ");
            String author = sc.nextLine();

            System.out.println("Введите количество копий: ");
            int availableCopies = Integer.parseInt(sc.nextLine());

            Book book = new Book(title, author, availableCopies);
            lib.addBook(book);
            System.out.println("Книга успешна добавлена в каталог!");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Количество копий должно быть положительным числом.");
        } catch (IllegalArgumentException | InvalidBookCopiesException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    //Выдать объект
    private static void issueABook() {
        System.out.println("Введите название книги: ");
        String title = sc.nextLine();

        try {
            lib.takeBook(title);
            System.out.println("Книга \"" + title + "\" успешно выдана!");
        } catch (NoAvailableCopiesException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    //Вернуть книгу
    private static void returnTheBook() {
        System.out.println("Введите название книги: ");
        String title = sc.nextLine();

        try {
            lib.returnBook(title);
            System.out.println("Книга \"" + title + "\" возвращена!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. Вывести каталог. " +
                "\n2. Добавить книгу. " +
                "\n3. Выдать книгу. " +
                "\n4. Вернуть книгу. " +
                "\n5. Выйти из приложения.");
        System.out.println("Выберите пункт из меню: ");
    }
}
