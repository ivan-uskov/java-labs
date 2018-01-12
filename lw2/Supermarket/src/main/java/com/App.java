package com;

public class App {
    private static final int STEPS = 20;
    private static final int SLEEP_TIME = 300;

    public static void main(String[] args) {
        SupermarketWatcher watcher = new SupermarketWatcher(System.out);
        Supermarket supermarket = new Supermarket(watcher);
        RandomSupermarketController c = new RandomSupermarketController();
        c.work(supermarket, STEPS, SLEEP_TIME);
    }
}