package com;

public class App {
    private enum Action {
        TakeProduct,
        AcceptCustomer,
        GoToCashDesk,
        ProcessCashDesk
    }

    private static final Action[] availableActions = Action.values();

    private static final int STEPS = 20;
    private static final int SLEEP_TIME = 300;

    public static void main(String[] args) {
        SupermarketWatcher watcher = new SupermarketWatcher(System.out);
        Supermarket supermarket = new Supermarket(watcher);

        supermarket.open();
        int times = STEPS;
        while (times-- >= 0) {
            doAction(supermarket);
            Utils.sleep(SLEEP_TIME);
        }
        supermarket.close();
    }

    private static void doAction(Supermarket supermarket) {
        Action randomAction = getRandomAction();
        if (randomAction == Action.AcceptCustomer || !supermarket.hasActiveCustomers()) {
            supermarket.addCustomer(createCustomer());
        } else if (randomAction == Action.ProcessCashDesk && supermarket.hasCustomersOnCashDesk()) {
            supermarket.processCashDesk();
        } else {
            int randomCustomerId = Utils.random(0, supermarket.activeCustomersCount());
            Customer customer = supermarket.getActiveCustomer(randomCustomerId);

            if (randomAction == Action.GoToCashDesk && !customer.isBasketEmpty() || !supermarket.hasProducts()) {
                supermarket.moveCustomerToCashDesk(randomCustomerId);
            } else if (randomAction == Action.TakeProduct) {
                supermarket.giveCustomerRandomProduct(new RandomProductSelector(), customer);
            }
        }
    }

    private static Customer createCustomer() {
        return (new CustomerProvider()).get();
    }

    private static Action getRandomAction() {
        return availableActions[Utils.random(0, availableActions.length)];
    }
}