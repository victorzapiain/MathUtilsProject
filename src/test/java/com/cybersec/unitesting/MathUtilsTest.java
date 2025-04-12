package com.cybersec.unitesting;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    private MathUtils mathUtils;

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @Test
    void testAdd() {
        assertEquals(9, mathUtils.add(6, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, mathUtils.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(10, mathUtils.multiply(5, 2));
    }

    @Test
    void testDivide() {
        assertEquals(5.0, mathUtils.divide(10, 2));
        assertEquals(-1.0, mathUtils.divide(5, 0));
    }
}