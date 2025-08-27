package org.schoolsorokin.javacore.collectionframework.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private static List<String> strList1 = new ArrayList<>();
    private static List<String> strList2 = new LinkedList<>();

    public static void main(String[] args) {
        addElement();
        System.out.println("1-й список: " + strList1);
        System.out.println("2-й список: " + strList2);

        insertElement();
        System.out.println("Добавление новых элементов");

        removeElement();
        System.out.println("Удаление элементов");

        System.out.println("Измененный 1-й список: " + strList1);
        System.out.println("Измененный 2-й список: " + strList2);
    }

    public static void addElement() {
        strList1.add("Хлеб");
        strList1.add("Молоко");
        strList1.add("Яйца");

        strList2.add("Тетрадь");
        strList2.add("Пенал");
        strList2.add("Ручка");
    }

    public static void insertElement() {
        strList1.add(1, "Картофель");
        strList2.add(1, "Дневник");
    }

    public static void removeElement() {
        strList1.remove(2);
        strList2.remove(2);
    }
}
