package com;

public class NetAddressBuilder {
    static Address build(Address ip, Address mask) {
        int[] ipParts = ip.getAsArray();
        int[] maskParts = mask.getAsArray();

        int[] resultParts = new int[ipParts.length];
        for (int i = 0; i < resultParts.length; ++i) {
            resultParts[i] = ipParts[i] & maskParts[i];
        }

        return new Address(resultParts);
    }
}
