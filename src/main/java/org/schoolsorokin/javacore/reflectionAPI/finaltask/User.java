package org.schoolsorokin.javacore.reflectionAPI.finaltask;

public class User {
    String name;

    @NotNull
    @Size(min = 18, max = 100)
    int age;

    @NotNull
    @Size(min = 1, max = 20)
    int workExperience;

    public User(String name, int age, int workExperience) {
        this.name = name;
        this.age = age;
        this.workExperience = workExperience;
    }
}
