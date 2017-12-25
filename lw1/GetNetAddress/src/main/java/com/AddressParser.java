package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AddressParser {
    private static final String ADDRESS_PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    private Pattern pattern;

    AddressParser() throws IllegalArgumentException {
        pattern = Pattern.compile(ADDRESS_PATTERN);
    }

    Address parse(String addressString) {
        Matcher matcher = pattern.matcher(addressString);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid address: " + addressString);
        }

        int[] bytes = new int[Address.LENGTH];
        String[] parts = addressString.split("\\.");
        for (int i = 0; i < parts.length; ++i) {
            bytes[i] = Integer.parseUnsignedInt(parts[i]);
        }
        return new Address(bytes);
    }
}
