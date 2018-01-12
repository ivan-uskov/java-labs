package com;

public class RandomSupermarketController {
    private enum Action {
        TakeProduct,
        AcceptCustomer,
        GoToCashDesk,
        ProcessCashDesk
    }
    private static final Action[] availableActions = Action.values();

    void work(Supermarket supermarket, int times, int sleepTime) {
        supermarket.open();
        while (times-- >= 0) {
            doAction(supermarket);
            Utils.sleep(sleepTime);
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
                supermarket.giveCustomerRandomProduct(new RandomProductSelector(customer.getType()), customer);
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
