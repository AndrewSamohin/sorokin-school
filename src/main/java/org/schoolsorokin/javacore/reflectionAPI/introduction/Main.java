package org.schoolsorokin.javacore.reflectionAPI.introduction;

public class Main {
    public static void main(String[] args) {
        Class clazz = Person.class;
        System.out.println("clazz - " + clazz.getName());

        System.out.println("intefaces: ");
        for(Class inteface : clazz.getInterfaces()){
            System.out.println(inteface);
        }

        int modifiers = clazz.getModifiers();
        System.out.println("modifiers: " + modifiers);
    }
}
