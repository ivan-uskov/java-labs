package com;

public class App {
    public static void main(String[] args) {
        try
        {
            if (args.length != 2) {
                throw new IllegalArgumentException("Not enough arguments, expected <ip> and <mask>");
            }

            AddressParser addressParser = new AddressParser();
            Address ip = addressParser.parse(args[0]);
            Address mask = addressParser.parse(args[1]);
            System.out.println(NetAddressBuilder.build(ip, mask));
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
