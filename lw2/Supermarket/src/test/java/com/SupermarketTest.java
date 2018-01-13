package com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {
    @Test
    void throwExceptionOnCloseAfterCloseAndOpenAfterOpen() {
        Supermarket s = new Supermarket();
        assertThrows(RuntimeException.class, s::close);

        TestUtils.assertNotThrows(s::open);
        assertThrows(RuntimeException.class, s::open);
        TestUtils.assertNotThrows(s::close);
        assertThrows(RuntimeException.class, s::close);
    }

    @Test
    void customersNotExistsAfterOpen() {
        Supermarket s = new Supermarket();
        assertFalse(s.hasActiveCustomers());
        assertFalse(s.hasCustomersOnCashDesk());
        assertEquals(0, s.activeCustomersCount());
        assertThrows(IndexOutOfBoundsException.class, () -> s.getActiveCustomer(0));
    }

    @Test
    void afterInsertionCustomerActive() {
        Supermarket s = new Supermarket();
        Customer c = (new CustomerProvider()).get();

        s.addCustomer(c);
        assertTrue(s.hasActiveCustomers());
        assertFalse(s.hasCustomersOnCashDesk());
        assertEquals(1, s.activeCustomersCount());
        assertEquals(c, s.getActiveCustomer(0));
    }

    @Test
    void afterCashDeskCustomerLeaving() {
        Supermarket s = new Supermarket();

        s.addCustomer((new CustomerProvider()).get());
        s.moveCustomerToCashDesk(0);
        assertTrue(s.hasCustomersOnCashDesk());
        s.processCashDesk();
        assertFalse(s.hasCustomersOnCashDesk());
    }

    @Test
    void thenCustomerTakeProductItRemovesFromShelves() {
        Supermarket s = new Supermarket();
        s.open();
        assertTrue(s.hasProducts());
        s.addCustomer((new CustomerProvider()).get());

        for (Product p : (new ProductSupplier()).getProducts()) {
            s.giveCustomerRandomProduct(products -> new ProductSelector.Choice(0, p.getQuantity()), s.getActiveCustomer(0));
        }

        assertFalse(s.hasProducts());
    }
}