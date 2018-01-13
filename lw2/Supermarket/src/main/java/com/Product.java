package com;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final Category category;

    public enum Category {
        AdultsOnly, ForAll
    }

    Product(String productName, int productPrice, int productQuantity, Category productCategory) {
        name = productName;
        price = productPrice;
        quantity = productQuantity;
        category = productCategory;
    }

    Product(String productName, int productPrice, int productQuantity) {
        this(productName, productPrice, productQuantity, Category.ForAll);
    }

    String getName() {
        return name;
    }

    Category getCategory() {
        return category;
    }

    int getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }

    boolean empty() {
        return quantity == 0;
    }

    Product split(int partQuantity) throws IllegalArgumentException {
        if (partQuantity <= 0) {
            throw new IllegalArgumentException("part quantity " + partQuantity + " can't be less than 1");
        }
        if (partQuantity > quantity) {
            throw new IllegalArgumentException("part quantity " + partQuantity + " can't be greater than product quantity " + quantity);
        }

        quantity -= partQuantity;
        return new Product(name, price, partQuantity, category);
    }
}
