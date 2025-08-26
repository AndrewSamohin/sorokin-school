package org.schoolsorokin.javacore.oop.oopReview;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            displayMenu();
            System.out.println("Select an option: ");

            int option = sc.nextInt();
            switch (option) {
                //Добавление новой публикации
                case 1:
                    System.out.println("Select publication type: (1 - Book, 2 - Magazine, 3 - Newspaper)");
                    int type = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter the title: ");
                    String title = sc.nextLine();

                    System.out.println("Enter the author: ");
                    String author = sc.nextLine();

                    System.out.println("Enter the year: ");
                    int year = sc.nextInt();
                    sc.nextLine();

                    if (type == 1) {
                        System.out.println("Enter the ISBN: ");
                        String ISBN = sc.nextLine();
                        library.addPublication(new Book(title, author, year, ISBN));
                    } else if (type == 2) {
                        System.out.println("Enter the issue number: ");
                        int issueNumber = sc.nextInt();
                        library.addPublication(new Magazine(title, author, year, issueNumber));
                    } else if (type == 3) {
                        System.out.println("Enter the publication day: ");
                        String publicationDay = sc.nextLine();
                        library.addPublication(new Newspaper(title, author, year, publicationDay));
                    }
                    break;
                //Вывод всех публикаций
                case 2:
                    library.listPublications();
                    break;
                //Поиск публикаций по автору
                case 3:
                    sc.nextLine();
                    System.out.println("Enter author to search: ");
                    String authorToSearch = sc.nextLine();
                    library.searchByAuthor(authorToSearch);
                    break;
                //Вывод общего количества публикаций
                case 4:
                    System.out.println("Total publications: " + Publication.getPublicationCount());
                    break;
                //Выход из программы
                case 5:
                    flag = false;
                    break;

                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    //Метод для вывода соновного меню
    public static void displayMenu() {
        System.out.println(
                "Option 1. Add a new publication. " +
                        "\nOption 2. Display a list of all publications. " +
                        "\nOption 3. Search for publication by author." +
                        "\nOption 4. Display the total number of publications. " +
                        "\nOption 5. Exit."
        );
    }
}
