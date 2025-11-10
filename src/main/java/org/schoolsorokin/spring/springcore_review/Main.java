package org.schoolsorokin.spring.springcore_review;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "org.schoolsorokin.spring.springcore_review"
                );
        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);

        listener.run();

        context.close();
    }
}
