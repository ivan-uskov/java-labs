package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Supermarket {
    private SupermarketWatcher watcher;
    private ProductSupplier productSupplier = new ProductSupplier();
    private CustomerProvider customerProvider = new CustomerProvider();
    private CashDesk cashDesk = new CashDesk(new Discount(Utils.random(10, 20)));
    private SupermarketReport report = new SupermarketReport();

    private ArrayList<Product> products;
    private ArrayList<Customer> activeCustomers = new ArrayList<>();
    private Queue<Customer> cashDeskQueue = new LinkedList<>();

    private enum Action {
        TakeProduct,
        AcceptCustomer,
        GoToCashDesk,
        ProcessCashDesk
    }

    private Action[] availableActions = Action.values();

    private final int SLEEP_TIME = 300;

    Supermarket(SupermarketWatcher w) {
        watcher = w;

        updateProducts();
    }

    void work(int times) {
        open();
        while (times-- >= 0) {
            doAction();
            Utils.sleep(SLEEP_TIME);
        }
        close();
    }

    private void updateProducts() {
        products = productSupplier.getProducts();
        watcher.handleNewProducts(products);
    }

    private void open() {
        watcher.handleOpened();
    }

    private void close() {
        report.addNotSoldProducts(this.products);
        watcher.handleClosed(report);
    }

    private void doAction() {
        Action randomAction = getRandomAction();
        if (randomAction == Action.AcceptCustomer || activeCustomers.size() == 0) {
            addNewCustomer();
        } else if (randomAction == Action.ProcessCashDesk && !cashDeskQueue.isEmpty()) {
            processCashDesk();
        } else {
            int randomCustomerId = Utils.random(0, activeCustomers.size());
            Customer customer = activeCustomers.get(randomCustomerId);

            if (randomAction == Action.GoToCashDesk && !customer.isBasketEmpty()) {
                moveCustomerToCashDesk(randomCustomerId, customer);
            } else if (randomAction == Action.TakeProduct) {
                giveCustomerRandomProduct(customer);
            }
        }
    }

    private void processCashDesk() {
        Customer customer = cashDeskQueue.poll();
        if (customer != null) {
            BillView bill = cashDesk.acceptCustomer(customer);
            report.addBill(bill);
            watcher.handleNewBill(bill);
        }
    }

    private Action getRandomAction() {
        return availableActions[Utils.random(0, availableActions.length)];
    }

    private void addNewCustomer() {
        Customer newCustomer = customerProvider.get();
        activeCustomers.add(newCustomer);
        watcher.handleNewCustomer(newCustomer);
    }

    private void giveCustomerRandomProduct(Customer customer) {
        int randomProductId = Utils.random(0, products.size());
        Product product = products.get(randomProductId);
        int randomQuantity = Utils.random(1, product.getQuantity() + 1);

        int newQuantity = product.getQuantity() - randomQuantity;
        if (newQuantity > 0) {
            products.set(randomProductId, new Product(product.getName(), product.getPrice(), newQuantity));
        } else {
            products.remove(randomProductId);
        }

        Product customerProduct = new Product(product.getName(), product.getPrice(), randomQuantity);
        customer.addProduct(customerProduct);
        watcher.handleCustomerPickedProduct(customer, customerProduct);
    }

    private void moveCustomerToCashDesk(int customerId, Customer customer) {
        activeCustomers.remove(customerId);
        cashDeskQueue.add(customer);
        watcher.handleCustomerGoToCashDesk(customer);
    }
}
