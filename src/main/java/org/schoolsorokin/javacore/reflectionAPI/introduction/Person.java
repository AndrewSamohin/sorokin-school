package org.schoolsorokin.javacore.reflectionAPI.introduction;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String sayHello() {
        return "Hello!";
    }
}
