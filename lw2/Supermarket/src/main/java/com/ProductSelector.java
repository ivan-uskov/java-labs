package com;

import java.util.ArrayList;

public interface ProductSelector {
    class Choice {
        private final int productId;
        private final int quantity;

        Choice(int id, int size) {
            this.productId = id;
            this.quantity = size;
        }

        int getProductId() {
            return productId;
        }

        int getQuantity() {
            return quantity;
        }
    }

    Choice select(ArrayList<Product> products);
}
