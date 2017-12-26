package com;

public class FNVHasher {
    static final int INITIAL_VALUE = 0x811c9dc5;

    private static final int PRIME = 0x01000193;

    private int accumulator;

    FNVHasher() {
        reset();
    }

    public void add(byte[] buffer, int bufferSize) {
        for (int i = 0; i < bufferSize; ++i) {
            accumulator *= PRIME;
            accumulator ^= buffer[i];
        }
    }

    public int getHash() {
        return accumulator;
    }

    public void reset() {
        accumulator = INITIAL_VALUE;
    }
}
