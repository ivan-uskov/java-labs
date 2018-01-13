package com;

class CashDesk {
    private final Discount discount;
    private PaymentMethod[] availablePaymentMethods = PaymentMethod.values();

    CashDesk(Discount retiredCustomersDiscount) {
        discount = retiredCustomersDiscount;
    }

    void setAvailablePaymentMethods(PaymentMethod[] availablePaymentMethods) {
        this.availablePaymentMethods = availablePaymentMethods;
    }

    BillView acceptCustomer(Customer customer) {
        Bill bill = new Bill(customer.getName(), customer.getType() == Customer.Type.Retired ? discount : null);
        bill.setPaymentMethod(getRandomPaymentMethod());
        bill.setPrice(
                customer
                        .getProducts()
                        .stream()
                        .mapToDouble((product) -> product.getPrice() * product.getQuantity())
                        .sum()
        );

        return bill;
    }

    private PaymentMethod getRandomPaymentMethod() {
        return availablePaymentMethods[Utils.random(0, availablePaymentMethods.length)];
    }
}
