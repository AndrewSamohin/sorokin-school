package org.schoolsorokin.javacore.reflectionAPI.annotations;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Method[] methods = Book.class.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Author.class)) {
                Author author = m.getAnnotation(Author.class);
                System.out.println("Метод: " + m.getName() + ", Автор: " + author.name());
            }
        }
    }
}
