package org.schoolsorokin.javacore.testing.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {
    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @Test
    void shouldAddNumbersCorrectly() {
        int a = 10;
        int b = 20;
        int result = a + b;
        assertEquals(30, result);
    }

    @Test
    void shouldMultiplyNumbersCorrectly() {
        int a = 5;
        int b = 3;
        int result = a * b;
        assertEquals(15, result);
    }

    @Test
    void shouldMultiplyNumbersInCorrectly() {
        int a = 5;
        int b = 0;
        int result = a * b;
        assertEquals(0, result);
    }

    @Test
    void shouldSubstractNumbersCorrectly() {
        int a = 5;
        int b = 3;
        int result = a - b;
        assertEquals(2,  result);
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }
}
