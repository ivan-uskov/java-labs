package com;

public class App {
    private static final int STEPS = 20;

    public static void main(String[] args) {
        SupermarketWatcher watcher = new SupermarketWatcher(System.out);
        Supermarket supermarket = new Supermarket(watcher);
        supermarket.work(STEPS);
    }
}