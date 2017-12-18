package com;

class AddressValidator {
    private static final int SUPPORT_ADDRESS_LENGTH = 4;

    AddressValidator(int addressPartsCount) throws IllegalArgumentException {
        if (addressPartsCount != SUPPORT_ADDRESS_LENGTH) {
            throw new IllegalArgumentException("Invalid address parts count " + Integer.toString(addressPartsCount));
        }
    }
}
