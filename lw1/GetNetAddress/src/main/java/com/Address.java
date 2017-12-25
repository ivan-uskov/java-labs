package com;

public class Address {
    public static final int LENGTH = 4;

    private int[] parts;

    Address(int... p) {
        if (p.length != LENGTH) {
            throw new IllegalArgumentException("Unexpected parts length " + p.length);
        }

        parts = p.clone();
    }

    /**
     * Returns array with length equals to LENGTH constant
     *
     * @return int[]
     */
    int[] getAsArray() {
        return parts;
    }

    public String toString() {
        String[] mapped = new String[parts.length];

        for (int i = 0; i < parts.length; ++i) {
            mapped[i] = Integer.toString(parts[i]);
        }

        return String.join(".", mapped);
    }
}
