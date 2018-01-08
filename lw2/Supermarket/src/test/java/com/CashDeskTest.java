package com;

import org.junit.jupiter.api.Test;

import javax.smartcardio.Card;

import static org.junit.jupiter.api.Assertions.*;

class CashDeskTest {
    @Test
    void acceptForEmptyBasketCreatesBillWithZeroAmount() {
        Customer customer = new Customer("Test customer", Customer.Type.Adult);
        CashDesk cashDesk = new CashDesk(null);
        BillView bill = cashDesk.acceptCustomer(customer);
        assertTrue(customer.isBasketEmpty());
        assertEquals(0, bill.getAmount());
    }

    @Test
    void acceptForNotEmptyBasketCreatesCorrectBill() {
        Customer customer = new Customer("Test customer", Customer.Type.Adult);
        customer.addProduct(new Product("Test product", 20, 5));
        CashDesk cashDesk = new CashDesk(null);
        BillView bill = cashDesk.acceptCustomer(customer);
        assertEquals(100, bill.getAmount());
    }

    @Test
    void acceptForNotEmptyBasketCreatesCorrectBillWithDiscount() {
        String customerName = "Test customer";
        Customer customer = new Customer(customerName, Customer.Type.Retired);
        customer.addProduct(new Product("Test product", 20, 5));
        CashDesk cashDesk = new CashDesk(new Discount(10));
        BillView bill = cashDesk.acceptCustomer(customer);
        assertEquals(90, bill.getAmount());
        assertEquals(customerName, bill.getCustomerName());
    }

    @Test
    void acceptReturnsBillWithConcretePaymentMethod() {
        Customer customer = new Customer("Test customer", Customer.Type.Adult);
        CashDesk cashDesk = new CashDesk(null);

        cashDesk.setAvailablePaymentMethods(new PaymentMethod[]{PaymentMethod.Cash});
        assertEquals(PaymentMethod.Cash, cashDesk.acceptCustomer(customer).getPaymentMethod());

        cashDesk.setAvailablePaymentMethods(new PaymentMethod[]{PaymentMethod.CreditCard});
        assertEquals(PaymentMethod.CreditCard, cashDesk.acceptCustomer(customer).getPaymentMethod());
    }
}