package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FNVHasherTest {
    private FNVHasher hasher;

    @BeforeEach
    void createHasher() {
        hasher = new FNVHasher();
    }

    @AfterEach
    void clearHasher() {
        hasher = null;
    }

    @Test
    void hashOfEmptySequenceEqualsInitialValue() {
        hasher.add(new byte[]{}, 0);
        assertEquals(FNVHasher.INITIAL_VALUE, hasher.getHash());
    }

    @Test
    void hashOfZeroHasExpectedValue() {
        hasher.add(new byte[]{0}, 1);
        assertEquals(0x50c5d1f, hasher.getHash());
    }

    @Test
    void resetSetupInitialValue() {
        hasher.add(new byte[]{0}, 1);
        assertNotEquals(FNVHasher.INITIAL_VALUE, hasher.getHash());
        hasher.reset();
        assertEquals(FNVHasher.INITIAL_VALUE, hasher.getHash());
    }
}