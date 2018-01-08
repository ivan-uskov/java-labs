package com;

public class Product {
    private String name;
    private int price;
    private int quantity;

    Product(String productName, int productPrice, int productQuantity) {
        name = productName;
        price = productPrice;
        quantity = productQuantity;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }
}
