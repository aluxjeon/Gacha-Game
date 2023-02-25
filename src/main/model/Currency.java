package model;

public class Currency {
    private int currency;

    public Currency() {
        currency = 1000;
    }

    //TODO: return current amount currency
    public Integer getCurrency() {
        return this.currency;
    }

    //TODO: Add amount to currency
    //TODO: Amount > 0
    public void addCurrency(int amount) {
        currency += amount;
        System.out.println("Completed!");
    }

    public void subCurrency(int amount) {
        currency -= amount;
        System.out.println("Completed!");

    }
}
