package org.schoolsorokin.javacore.streamAPI.student;

import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private final String name;

    private final int age;

    private final double averageScore;

    private final boolean fromOrganization;

    private final char sex;

    public Student(String name,
                   int age,
                   double averageScore,
                   boolean fromOrganization,
                   char sex) {
        this.name = name;
        this.age = age;
        this.averageScore = averageScore;
        this.fromOrganization = fromOrganization;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public boolean isFromOrganization() {
        return fromOrganization;
    }

    public char getSex() {
        return sex;
    }
    //Универсальный метод
    public static List<Student> filterStudent(List<Student> students, Predicate<Student> filter) {
        return students.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Ваня", 24, 4.0, true, 'М'),
                new Student("Маша", 19, 3.4, false, 'Ж'),
                new Student("Рома", 23, 4.5, true, 'М'),
                new Student("Оля", 21, 3.7, false, 'Ж')
        );

        Predicate<Student> adults = student -> student.getAge() > 20;
        System.out.println("Студенты старше 20: ");
        filterStudent(students, adults).forEach(System.out::println);

        Predicate<Student> children = student -> student.getAge() < 20;
        System.out.println("Студенты младше 20: ");
        filterStudent(students, children).forEach(System.out::println);

        List<Student> daughters = students.stream()
                .filter(Student::isFromOrganization)
                .collect(Collectors.toList());
        System.out.println("Студенты из компании: ");
        daughters.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", averageScore=" + averageScore +
                ", fromOrganization=" + fromOrganization +
                ", sex=" + sex +
                '}';
    }
}
