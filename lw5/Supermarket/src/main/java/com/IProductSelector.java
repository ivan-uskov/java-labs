package com;

import java.util.ArrayList;

public interface IProductSelector {
    class Choice {
        private int productId;
        private int quantity;

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
