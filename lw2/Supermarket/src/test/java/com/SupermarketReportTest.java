package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketReportTest {
    @Test
    void reportAgregatesAmount() {
        Bill bill = new Bill("Someone");
        bill.setPrice(12.5);
        Bill anotherBill = new Bill("Another");
        anotherBill.setPrice(10);

        SupermarketReport report = new SupermarketReport();
        report.addBill(bill);
        report.addBill(anotherBill);

        assertEquals(22.5, report.getFullAmount());
    }

    @Test
    void notSoldProductsExistsInReport() {
        String[] productNames = new String[]{"Milk", "Potato"};

        ArrayList<Product> notSoldProducts = new ArrayList<>();
        for (String name : productNames) {
            notSoldProducts.add(new Product(name, 3, 3));
        }

        SupermarketReport report = new SupermarketReport();
        report.addNotSoldProducts(notSoldProducts);
        String reportStr = report.toString();

        for (String name : productNames) {
            assertTrue(reportStr.contains(name));
        }
    }
}