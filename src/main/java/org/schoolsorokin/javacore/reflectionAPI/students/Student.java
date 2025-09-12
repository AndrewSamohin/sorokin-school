package org.schoolsorokin.javacore.reflectionAPI.students;

public class Student {
    private String name;
    private int age;
    public String university;

    public Student(String name, int age, String university) {
        this.name = name;
        this.age = age;
        this.university = university;
    }

    private Student() {

    }

    public void introduce() {
        System.out.println("Привет, меня зовут " + name +
                ", мне " + age + " лет, " +
                "и я учусь в " + university + ".");
    }

    private void studySecretly() {
        System.out.println(name + " тайно занимается учёбой...");
    }
}
