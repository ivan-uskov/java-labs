package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Supermarket {
    private SupermarketWatcher watcher;
    private ProductSupplier productSupplier = new ProductSupplier();
    private CashDesk cashDesk = new CashDesk(new Discount(Utils.random(10, 20)));
    private SupermarketReport report = new SupermarketReport();

    private ArrayList<Product> products;
    private ArrayList<Customer> activeCustomers = new ArrayList<>();
    private Queue<Customer> cashDeskQueue = new LinkedList<>();

    private enum State {
        Opened,
        Closed
    }

    private State state = State.Closed;

    Supermarket() {
        this(new SupermarketWatcher());
    }

    Supermarket(SupermarketWatcher w) {
        watcher = w;

        updateProducts();
    }

    void open() {
        if (state != State.Closed) {
            throw new RuntimeException("State should be closed before supermarket open");
        }

        state = State.Opened;
        watcher.handleOpened();
    }

    void close() {
        if (state != State.Opened) {
            throw new RuntimeException("State should be opened before supermarket closed");
        }

        state = State.Closed;
        report.addNotSoldProducts(products);
        watcher.handleClosed(report);
    }

    boolean hasActiveCustomers() {
        return !activeCustomers.isEmpty();
    }

    boolean hasCustomersOnCashDesk() {
        return !cashDeskQueue.isEmpty();
    }

    boolean hasProducts() {
        return !products.isEmpty();
    }

    int activeCustomersCount() {
        return activeCustomers.size();
    }

    Customer getActiveCustomer(int i) throws IndexOutOfBoundsException {
        return activeCustomers.get(i);
    }

    void processCashDesk() {
        Customer customer = cashDeskQueue.poll();
        if (customer != null) {
            BillView bill = cashDesk.acceptCustomer(customer);
            report.addBill(bill);
            watcher.handleNewBill(bill);
        }
    }

    void addCustomer(Customer customer) {
        activeCustomers.add(customer);
        watcher.handleNewCustomer(customer);
    }

    void giveCustomerRandomProduct(IProductSelector productSelector, Customer customer) {
        RandomProductSelector.Choice c = productSelector.select(products);
        Product product = products.get(c.getProductId());
        Product part = product.split(c.getQuantity());

        if (product.empty()) {
            products.remove(c.getProductId());
        }

        customer.addProduct(part);
        watcher.handleCustomerPickedProduct(customer, part);
    }

    void moveCustomerToCashDesk(int customerId) {
        Customer customer = getActiveCustomer(customerId);
        activeCustomers.remove(customerId);
        cashDeskQueue.add(customer);
        watcher.handleCustomerGoToCashDesk(customer);
    }

    private void updateProducts() {
        products = productSupplier.getProducts();
        watcher.handleNewProducts(products);
    }
}
