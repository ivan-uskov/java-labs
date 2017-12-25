package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetAddressBuilderTest {
    @Test
    void resultAddressIsABinaryAndOfProvidedAddresses() {
        Address ip = new Address(192, 168, 1, 2);
        Address mask = new Address(255, 255, 254, 0);
        assertEquals("192.168.0.0", NetAddressBuilder.build(ip, mask).toString());
    }
}