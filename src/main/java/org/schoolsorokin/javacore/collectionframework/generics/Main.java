package org.schoolsorokin.javacore.collectionframework.generics;

public class Main {
    public static void main(String[] args) {
        GenericBox<Integer> intBox1 = new GenericBox<>(10);
        GenericBox<Integer> intBox2 = new GenericBox<>(20);
        GenericBox<Integer> intBox3 = new GenericBox<>(20);

        GenericBox<String> stringBox1 = new GenericBox<>("Hello");
        GenericBox<String> stringBox2 = new GenericBox<>("World");
        GenericBox<String> stringBox3 = new GenericBox<>("Hello");

        System.out.println("intBox1: " + intBox1.getContent());
        System.out.println("intBox2: " + intBox2.getContent());
        System.out.println("intBox3: " + intBox3.getContent());
        System.out.println("stringBox1: " + stringBox1.getContent());
        System.out.println("stringBox2: " + stringBox2.getContent());
        System.out.println("stringBox3: " + stringBox3.getContent());

        System.out.println("intBox1 равен intBox2? " + intBox1.isEqual(intBox2));
        System.out.println("intBox2 равен intBox3? " + intBox2.isEqual(intBox3));

        intBox1.swap(intBox2);
        System.out.println("intBox2 равен intBox3 после замены? " + intBox2.isEqual(intBox3));

        System.out.println("stringBox1 равен stringBox2? " + stringBox1.isEqual(stringBox2));
        System.out.println("stringBox1 равен stringBox3? " + stringBox1.isEqual(stringBox3));

        stringBox1.swap(stringBox2);
        System.out.println("stringBox1 равен stringBox3 после замены? " + stringBox1.isEqual(stringBox3));

    }
}
