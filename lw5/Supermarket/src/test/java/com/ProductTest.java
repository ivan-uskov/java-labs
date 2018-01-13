package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static final int QUANTITY = 5;
    private Product testProduct;

    @BeforeEach
    void constructProduct() {
        testProduct = new Product("Test product", 3, QUANTITY);
    }

    @AfterEach
    void destructProduct() {
        testProduct = null;
    }

    @Test
    void constructPartWithInvalidQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> testProduct.split(QUANTITY + 1));
        assertThrows(IllegalArgumentException.class, () -> testProduct.split(0));
        assertThrows(IllegalArgumentException.class, () -> testProduct.split(-1));
    }

    @Test
    void partHasSameNameAndPriceAndCategory() {
        Product part = testProduct.split(QUANTITY / 2);
        assertEquals(testProduct.getName(), part.getName());
        assertEquals(testProduct.getPrice(), part.getPrice());
        assertEquals(testProduct.getCategory(), part.getCategory());
    }

    @Test
    void getPartsRemovesProductQuantity() {
        testProduct.split(QUANTITY);
        assertEquals(0, testProduct.getQuantity());
        assertTrue(testProduct.empty());
    }
}