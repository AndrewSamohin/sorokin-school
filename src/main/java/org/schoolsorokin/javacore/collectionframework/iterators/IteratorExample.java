package org.schoolsorokin.javacore.collectionframework.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 != 0) {
                iterator.remove();
            }
        }

        System.out.println("Список после удаления нечетных чисел: ");
        for (Integer i : list) {
            System.out.println(i + " ");
        }
    }
}
