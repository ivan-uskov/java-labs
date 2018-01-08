package com;

import java.io.*;
import java.util.ArrayList;

public class SupermarketReport {
    private ArrayList<BillView> bills = new ArrayList<>();
    private ArrayList<Product> notSoldProducts;

    void addNotSoldProducts(ArrayList<Product> products) {
        notSoldProducts = products;
    }

    void addBill(BillView bill) {
        bills.add(bill);
    }

    public String toString() {
        PrintStream ps = Utils.stringPrintStream();

        ps.println("Report");
        ps.println("Total amount: " + getFullAmount());
        ps.println("Not sold products:");
        for (Product product: notSoldProducts) {
            ps.println(" - " + product.getName() + " quantity " + product.getQuantity());
        }

        return ps.toString();
    }

    private double getFullAmount() {
        return bills.stream().mapToDouble(BillView::getAmount).sum();
    }
}
