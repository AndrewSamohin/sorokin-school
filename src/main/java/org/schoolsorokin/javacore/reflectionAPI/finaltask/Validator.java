package org.schoolsorokin.javacore.reflectionAPI.finaltask;

import java.lang.reflect.Field;

public class Validator {
    public static void validate(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(NotNull.class)) {
                if (field.get(obj) == null) {
                    System.out.println("Field " + field.getName() + " is not null!");
                }
            }

            if (field.isAnnotationPresent(Size.class)) {
                Size size = field.getAnnotation(Size.class);

                Object fieldValue = field.get(obj);

                if (fieldValue instanceof Integer value) {
                    if (value >= size.min() && value <= size.max()) {
                        System.out.println("Field " + field.getName() +
                                " удовлетворяет ограничению по размеру. Min: " + size.min() +
                                " Max: " + size.max());
                    }
                    else {
                        System.out.println("Field " + field.getName() +
                                " не удовлетворяет ограничению. Min: " + size.min() +
                                " Max: " + size.max());
                    }
                }
            }
        }
    }
}