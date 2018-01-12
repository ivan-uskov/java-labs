package com;

class Bill implements BillView {
    private String customer;
    private double price;
    private Discount discount;
    private PaymentMethod paymentMethod = PaymentMethod.Cash;

    Bill(String customerName, Discount d) {
        customer = customerName;
        discount = d;
    }

    Bill(String customerName) {
        this(customerName, null);
    }

    @Override
    public double getAmount() {
        return discount != null ? discount.accept(price) : price;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String getCustomerName() {
        return customer;
    }

    void setPrice(double p) {
        price = p;
    }

    void setPaymentMethod(PaymentMethod pm) {
        paymentMethod = pm;
    }
}
