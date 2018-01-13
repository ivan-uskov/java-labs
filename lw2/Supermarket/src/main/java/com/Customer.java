package com;

import java.util.ArrayList;

public class Customer {
    public enum Type {
        Child, Adult, Retired
    }

    private final String name;
    private final Type type;

    private final ArrayList<Product> products = new ArrayList<>();

    Customer(String customerName, Type customerType) {
        name = customerName;
        type = customerType;
    }

    Customer(String customerName) {
        this(customerName, Type.Adult);
    }

    String getName() {
        return name;
    }

    Type getType() {
        return type;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    boolean isBasketEmpty() {
        return products.size() == 0;
    }

    ArrayList<Product> getProducts() {
        return products;
    }
}
