package com;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Utils {
    static void assertNotThrows(Executable executable) {
        try {
            executable.execute();
        } catch (Throwable e) {
            assertEquals("", e.getMessage());
        }
    }
}
