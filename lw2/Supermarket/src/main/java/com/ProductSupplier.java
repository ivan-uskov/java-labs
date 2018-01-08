package com;

import java.util.ArrayList;

class ProductSupplier {
    ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("Milk", 20, 30));
        list.add(new Product("Cigarettes", 35, 10));
        list.add(new Product("Potato", 10, 50));
        list.add(new Product("Sausage", 40, 50));

        return list;
    }
}
