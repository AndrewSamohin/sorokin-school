package org.schoolsorokin.javacore.testing.dirtytest;

import org.junit.jupiter.api.Test;
import org.schoolsorokin.javacore.testing.calculator.Calculator;

import static junit.framework.Assert.assertEquals;

public class DirtyCalculatorTest {
    public static Calculator createDefaultCalculator(){
        return new Calculator();
    }

    @Test
    void testSum() {
        assertEquals(12, createDefaultCalculator().sum(4, 8));
    }

    @Test
    void testSubtract() {
        assertEquals(5,  createDefaultCalculator().subtract(7, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(15, createDefaultCalculator().multiply(3, 5));
    }
}
