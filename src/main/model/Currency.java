package model;

// Represents a currency with initially 1000 units
public class Currency {
    private int currency = 1000;

    //EFFECTS: Makes a new currency with the initial amount you have to 1000
    public Currency() {}

    //EFFECTS: Returns the amount of currency you have
    public Integer getCurrency() {
        return this.currency;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: Adds 'amount' to currency and prints out completed to indicate completion
    public void addCurrency(int amount) {
        currency += amount;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: Subtracts 'amount' from currency and prints out completed to indicate completion
    public void subCurrency(int amount) {
        currency -= amount;
    }
}
