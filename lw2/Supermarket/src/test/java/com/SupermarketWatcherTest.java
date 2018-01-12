package com;

import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketWatcherTest {
    @FunctionalInterface
    private interface Function {
        void execute();
    }

    private boolean isExecuted = false;

    @Test
    void checkMethodAppendsToLogStream() {
        PrintStream strm = getPrintStream();
        SupermarketWatcher watcher = new SupermarketWatcher(strm);
        assertExecutes(watcher::handleOpened, "handleOpened should write something");
        assertExecutes(() -> watcher.handleClosed(new SupermarketReport()), "handleClosed should write something");
        assertExecutes(() -> watcher.handleNewProducts((new ProductSupplier()).getProducts()), "handleNewProducts should write something");
        assertExecutes(() -> watcher.handleNewCustomer(new Customer("Test Customer")), "handleNewCustomer should write something");
        assertExecutes(() -> watcher.handleCustomerGoToCashDesk(new Customer("Test Customer")), "handleCustomerGoToCashDesk should write something");
        assertExecutes(() -> watcher.handleNewBill(new Bill("Test Customer")), "handleNewBill should write something");
        assertExecutes(() ->
                watcher.handleCustomerPickedProduct(new Customer("Test Customer"), new Product("", 1, 1)),
                "handleCustomerPickedProduct should write something"
        );

        watcher.handleOpened();
    }

    private void assertExecutes(Function job, String message) {
        isExecuted = false;
        job.execute();
        assertTrue(isExecuted, message);
    }

    private PrintStream getPrintStream() {
        StringWriter sw = new StringWriter();
        OutputStream os = new OutputStream() {
            @Override
            public void write(int b) {
                sw.write(b);
                isExecuted = true;
            }
        };
        return new PrintStream(os) {
            @Override
            public String toString() {
                return sw.toString();
            }
        };
    }
}