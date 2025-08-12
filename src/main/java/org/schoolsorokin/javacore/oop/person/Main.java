package org.schoolsorokin.javacore.oop.person;

public class Main {

    public void main() {
        int age = 5;
        double height = 1.89;
        boolean isStudent = true;

        System.out.println("Возраст: " + age + "\nРост: " + height + "\nСтудент: " + isStudent);

        Person person = new Person("Рома", 25);
        System.out.println("Имя: " + person.name + "\nВозраст: " + person.age);

        System.gc();
    }

}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
