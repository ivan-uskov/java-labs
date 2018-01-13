package com;

class Discount {
    private int size;

    Discount(int discountSize) {
        if (discountSize < 1 || 99 < discountSize) {
            throw new IllegalArgumentException("Invalid discount size");
        }

        size = discountSize;
    }

    double accept(double amount) {
        return amount - amount * ((double)size / 100);
    }
}
