package com;

import java.util.ArrayList;

class RandomProductSelector implements IProductSelector {
    public Choice select(ArrayList<Product> products) {
        int randomProductId = Utils.random(0, products.size());
        Product product = products.get(randomProductId);
        int randomQuantity = Utils.random(1, product.getQuantity() + 1);

        return new Choice(randomProductId, randomQuantity);
    }
}
