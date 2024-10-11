package com.thealgorithms.stacks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class GreatestElementConstantTimeTest {

    private GreatestElementConstantTime constantTime;

    @BeforeEach
    public void setConstantTime() {
        constantTime = new GreatestElementConstantTime();
    }

    @Test
    public void testMaxAtFirst() {
        constantTime.push(1);
        constantTime.push(10);
        constantTime.push(20);
        constantTime.push(5);
        assertEquals(20, constantTime.getMaximumElement());
    }

    @Test
    public void testMinTwo() {
        constantTime.push(5);
        constantTime.push(10);
        constantTime.push(20);
        constantTime.push(1);
        assertEquals(20, constantTime.getMaximumElement());
        constantTime.pop();
        constantTime.pop();
        assertEquals(10, constantTime.getMaximumElement());
    }

    @Test
    public void testNullMax() {
        constantTime.push(10);
        constantTime.push(20);
        constantTime.pop();
        constantTime.pop();
        assertNull(constantTime.getMaximumElement());
    }

    @Test
    public void testBlankHandle() {
        constantTime.push(10);
        constantTime.push(1);
        constantTime.pop();
        constantTime.pop();
        assertThrows(NoSuchElementException.class, () -> constantTime.pop());
    }
}
