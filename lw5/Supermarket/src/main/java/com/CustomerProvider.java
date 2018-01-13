package com;

import com.Customer.Type;

public class CustomerProvider {
    private String[] availableNames = new String[]{"John", "Bob", "Carl", "Nick"};
    private Type[] availableTypes = new Type[]{Type.Child, Type.Adult, Type.Retired};

    Customer get() {
        return new Customer(getRandomName(), getRandomType());
    }

    private String getRandomName() {
        return availableNames[Utils.random(0, availableNames.length)];
    }

    private Type getRandomType() {
        return availableTypes[Utils.random(0, availableTypes.length)];
    }
}
