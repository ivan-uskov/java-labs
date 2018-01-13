package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomProductSelectorTest {
    @Test
    void selectReturnsExistingValues() {
        ArrayList<Product> products = (new ProductSupplier()).getProducts();
        while(!products.isEmpty()) {
            RandomProductSelector s = new RandomProductSelector();
            ProductSelector.Choice c = s.select(products);

            final Product[] p = new Product[]{null, null};
            TestUtils.assertNotThrows(() -> p[0] = products.get(c.getProductId()));

            int originalQuantity = p[0].getQuantity();
            TestUtils.assertNotThrows(() -> p[1] = p[0].split(c.getQuantity()));
            assertEquals(originalQuantity, p[0].getQuantity() + p[1].getQuantity());
            if (p[0].empty()) {
                products.remove(p[0]);
            }
        }
    }
}