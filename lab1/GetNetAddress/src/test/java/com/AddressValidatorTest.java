package com;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {
    @Test
    void addressValidatorThrowsExceptionIfArgumentNotFour() {
        assertThrows(IllegalArgumentException.class, () -> new AddressValidator(5));
        assertThrows(IllegalArgumentException.class, () -> new AddressValidator(3));
        Utils.assertNotThrows(() -> new AddressValidator(4));
    }
}