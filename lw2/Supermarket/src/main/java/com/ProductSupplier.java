package com;

import java.util.ArrayList;
import com.Product.Category;

class ProductSupplier {
    ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("Milk", 20, 30, Category.ForAll));
        list.add(new Product("Cigarettes", 35, 10, Category.AdultsOnly));
        list.add(new Product("Potato", 10, 50, Category.ForAll));
        list.add(new Product("Sausage", 40, 50, Category.ForAll));

        return list;
    }
}
