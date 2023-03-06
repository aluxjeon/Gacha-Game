package model;

public interface Collectible {
    // EFFECTS: Randomly select something. Then update inventory list with a new whatever you pulled
    void pull();

    // EFFECTS: Perform pull() 10 times in a row
    void tenPull();
}
