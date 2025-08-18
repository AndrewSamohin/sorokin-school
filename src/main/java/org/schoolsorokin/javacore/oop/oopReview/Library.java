package org.schoolsorokin.javacore.oop.oopReview;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Publication> publications;

    public Library() {
        publications = new ArrayList<>();
    }

    //Добавление публикации в каталог
    public void addPublication(Publication pub) {
        publications.add(pub);
        Publication.increasePublicationCount();
        System.out.println("Publication added");
    }

    //Вывод всех публикаций
    public void listPublications() {
        for (Publication pub : publications) {
            System.out.println(pub);
        }
    }

    //Поиск и вывод публикаций
    public void searchByAuthor(String author) {
        for (Publication pub : publications) {
            if (pub.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(pub);
            }
        }
        System.out.println("No publications found for author " + author);
    }

    //Удаление публикаций
    public void deletePublication(Publication pub) {
        if (publications.remove(pub)) {
            System.out.println("Publication deleted");
        } else System.out.println("Publication not found");
    }
}
