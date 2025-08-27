package org.schoolsorokin.javacore.collectionframework.map;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("Яблоко", 100);
        map.put("Банан", 80);
        map.put("Апельсин", 120);

        System.out.println("Начальное содержимое HashMap: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Фрукт: " + entry.getKey() + ", Цена: " + entry.getValue());
        }

        map.put("Банан", 90);
        System.out.println("\nПосле обновления цены на банан: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Фрукт: " + entry.getKey() + ", Цена: " + entry.getValue());
        }

        map.remove("Апельсин");
        System.out.println("\nПосле удаления Апельсина: ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Фрукт: " + entry.getKey() + ", Цена: " + entry.getValue());
        }
    }
}
