package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {
    @Test
    void acceptReturnsAmountWithoutDiscountedPart() {
        assertEquals( 80, (new Discount(20)).accept(100));
        assertEquals( 98.56, (new Discount(12)).accept(112));
    }

    @Test
    void constructorWithSizeGreaterThan99ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Discount(100));
    }

    @Test
    void constructorWithSizeLessThan1ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Discount(0));
    }
}