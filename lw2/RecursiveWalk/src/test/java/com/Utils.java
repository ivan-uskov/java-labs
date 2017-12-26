package com;

import static org.junit.jupiter.api.Assertions.fail;

public class Utils {
    @FunctionalInterface
    public interface Command<T> {
        T execute() throws Throwable;
    }

    static <T> T exceptionSafe(Command<T> command) {
        try {
            return command.execute();
        } catch (Throwable e) {
            return fail("Exception " + e.getMessage());
        }
    }

    static <T> T nullSafe(T val) {
        if (val == null) {
            fail("Unexpected null");
        }

        return val;
    }
}
