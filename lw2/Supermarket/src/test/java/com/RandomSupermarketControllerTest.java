package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomSupermarketControllerTest {
    @Test
    void supermarketClosedAfterWork() {
        Supermarket s = new Supermarket();
        RandomSupermarketController c = new RandomSupermarketController();
        c.work(s, 15, 1);
        assertThrows(RuntimeException.class, s::close);
    }
}