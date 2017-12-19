package com;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class AddressParserTest {
    @Test
    void parserThrowsExceptionIfAddressInvalid() {
        AddressParser parser = new AddressParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("256"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("256.256.256.256"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("192.168.21.8a"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("a192.168.21.8"));
        Utils.assertNotThrows(() -> parser.parse("192.168.21.8"));
    }

    @Test
    void parserReturnsArrayOfIntegers() {
        AddressParser parser = new AddressParser();
        assertEquals("192.168.21.8", parser.parse("192.168.21.8").toString());
    }
}