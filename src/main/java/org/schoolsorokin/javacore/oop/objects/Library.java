package org.schoolsorokin.javacore.oop.objects;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Капитанская дочка", "Пушкин");
        Book book2 = new Book("Капитанская дочка", "Пушкин");
        Book book3 = new Book("Преступление и наказание", "Достоевский");

        System.out.println("-ToString-");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("-Equals-");
        System.out.println("book1 -> book2 = " + book1.equals(book2));
        System.out.println("book1 -> book3 = " + book1.equals(book3));

        System.out.println("-HashCode-");
        System.out.println("HashCode book1 - " + book1.hashCode());
        System.out.println("HashCode book2 - " + book2.hashCode());
        System.out.println("HashCode book2 - " + book3.hashCode());
    }
}
