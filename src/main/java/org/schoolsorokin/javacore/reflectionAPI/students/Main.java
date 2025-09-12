package org.schoolsorokin.javacore.reflectionAPI.students;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            //Вызов приватного конструктора
            Constructor<?> constructor = Student.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            Student objPrivate = (Student) constructor.newInstance();
            System.out.println("Объект приватного конструктора: " +  objPrivate);

            //Вывод всех полей
            Class clazz = Student.class;
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Все поля: " + field.getName());
            }

            //Новое значение для name
            Student newName = new Student("Виктор", 23, "ТулГу");
            Field fieldName = Student.class.getDeclaredField("name");
            fieldName.setAccessible(true);

            fieldName.set(newName, "Таня");
            String newValue = (String) fieldName.get(newName);
            System.out.println("Новое значение: " + newValue);

            //Вызов приватного метода
            Method secretMethod = clazz.getDeclaredMethod("introduce");
            secretMethod.setAccessible(true);
            secretMethod.invoke(newName);
        }
        catch (NoSuchMethodException
               | InstantiationException
               | IllegalAccessException
               | InvocationTargetException
               | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
