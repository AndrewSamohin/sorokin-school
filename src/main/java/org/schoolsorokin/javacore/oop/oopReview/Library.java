package org.schoolsorokin.javacore.oop.oopReview;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<Publication> publications;

    public Library() {
        publications = new ArrayList<>();
    }

    //Добавление публикации в каталог
    public void addPublication(Publication pub) {
        if (pub.getTitle() == null || pub.getTitle().trim().isEmpty()
                || pub.getAuthor() == null || pub.getAuthor().trim().isEmpty()) {
            System.out.println("Cannot add publication with empty title or author");
            return;
        }
        publications.add(pub);
        Publication.increasePublicationCount();
        System.out.println("Publication added");
    }

    //Вывод всех публикаций
    public void listPublications() {
        for (Publication pub : publications) {
            pub.printDetails();
        }
    }

    //Поиск и вывод публикаций
    public void searchByAuthor(String author) {
        for (Publication pub : publications) {
            if (pub.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(pub);
            } else System.out.println("No publications found for author " + author);
        }
    }
}
