package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerProviderTest {
    @Test
    void getReturnsNotNullCustomer() {
        assertNotNull((new CustomerProvider()).get());
    }
}