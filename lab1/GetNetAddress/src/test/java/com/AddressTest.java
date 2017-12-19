package com;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void addressConstructorThrowExceptionIfPartsSizeNotEqualsToPartsLength() {
        assertThrows(IllegalArgumentException.class, () -> new Address(192, 168, 21, 8, 5));
        assertThrows(IllegalArgumentException.class, () -> new Address(192, 168, 21));
        Utils.assertNotThrows(() -> new Address(192, 168, 21, 8));
    }

    @Test
    void addressConvertedToStringAsNumbersSeparatedByDots() {
        assertEquals("192.168.21.8", new Address(192, 168, 21, 8).toString());
    }

    @Test
    void addressHoldsCopyOfSourceArray() {
        int[] parts = {192, 168, 21, 8};
        Address address = new Address(parts);
        assertNotEquals(parts, address.getAsArray());
        assertTrue(Arrays.equals(parts, address.getAsArray()));
    }
}