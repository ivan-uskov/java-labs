package com;

import java.util.ArrayList;

class RandomProductSelector implements ProductSelector {
    private final Customer.Type customerType;

    RandomProductSelector() {
        this(Customer.Type.Adult);
    }

    RandomProductSelector(Customer.Type type) {
        customerType = type;
    }

    public Choice select(ArrayList<Product> products) {
        int[] keys = Utils.range(0, products.size());
        int[] filteredKeys = filterInts(keys, i -> checkProduct(products.get(i)));

        int randomProductId = filteredKeys[Utils.random(0, filteredKeys.length)];
        Product product = products.get(randomProductId);
        int randomQuantity = Utils.random(1, product.getQuantity() + 1);

        return new Choice(randomProductId, randomQuantity);
    }

    private boolean checkProduct(Product p) {
        return p.getCategory() != Product.Category.AdultsOnly || customerType != Customer.Type.Child;
    }

    @FunctionalInterface
    interface FilterIntPredicate {
        boolean execute(int t);
    }

    private int[] filterInts(int[] list, FilterIntPredicate p) {
        int newCount = 0;
        for (int i : list) {
            if (p.execute(i)){
                newCount++;
            }
        }
        int[] newInts = new int[newCount];
        int i = 0;
        for (int in : list) {
            if (p.execute(in)) {
                newInts[i++] = in;
            }
        }

        return newInts;
    }
}