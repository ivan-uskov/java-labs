package com;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

class SupermarketWatcher {
    private PrintStream logStream;

    SupermarketWatcher(PrintStream logStrm) {
        logStream = logStrm;
    }

    void handleOpened() {
        logStream.println(prepareTimeMessage() + " supermarket opened");
    }

    void handleClosed(SupermarketReport report) {
        logStream.println(prepareTimeMessage() + " supermarket closed");
        logStream.println(prepareTimeMessage() + report.toString());
    }

    void handleNewProducts(ArrayList<Product> products) {
        String timeMessage = prepareTimeMessage();
        logStream.println(timeMessage + " Supermarket products have been formed:");
        for (Product product : products) {
            logStream.println(timeMessage + "  - " + product.getName() + ", price: " + product.getPrice() + ", quantity: " + product.getQuantity());
        }
    }

    void handleNewCustomer(Customer customer) {
        logStream.println(prepareTimeMessage() + " New Customer with name: " + customer.getName() + " and type: " + customer.getType().toString());
    }

    void handleCustomerPickedProduct(Customer customer, Product product) {
        logStream.println(prepareTimeMessage() + " Customer " + customer.getName() + " picked " + product.getQuantity() + " units of " + product.getName());
    }

    void handleCustomerGoToCashDesk(Customer customer) {
        logStream.println(prepareTimeMessage() + " Customer " + customer.getName() + "  go to CashDesk");
    }

    void handleNewBill(BillView bill) {
        logStream.println(prepareTimeMessage() + " Customer " + bill.getCustomerName() + " pay the bill with amount " + bill.getAmount() + " and payment method " + bill.getPaymentMethod().toString());
    }

    private String prepareTimeMessage() {
        return "[" + LocalDateTime.now() + "]";
    }
}
