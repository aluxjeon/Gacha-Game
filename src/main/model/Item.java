package model;


//Represents an item that has a name, rarity, if it being held or not, and the number of copies
public class Item {
    private final String name;
    private final int rarity;
    private boolean equipped;
    private int copies;

    // EFFECTS: name of item is set to name, rarity of item is set to rarity,
    // whether the item is held by a character or not is set by equipped, the number of copies of the item
    // is set by copies
    public Item(String name, int rarity, boolean equipped,int copies) {
        this.name = name;
        this.rarity = rarity;
        this.equipped = equipped;
        this.copies = copies;
    }

    //EFFECTS: Returns name of item
    public String getName() {
        return this.name;
    }

    //EFFECTS: Returns rarity of item
    public int getRarity() {
        return this.rarity;
    }

    //EFFECTS: Returns the number of copies of the item
    public int getCopies() {
        return this.copies;
    }

    //MODIFIES: this
    //EFFECTS: Adds 1 to the number of copies of item
    public void addCopies() {
        this.copies++;
    }

    //MODIFIES: this
    //EFFECTS: Sets equipped boolean to true — indicates that the item is equipped
    public void equip() {
        equipped = true;
    }

    //MODIFIES: this
    //EFFECTS: Sets equipped boolean to false — indicates that the item is unequipped
    public void unequip() {
        equipped = false;
    }

    //EFFECTS: Returns whether the item is equipped or not with a boolean
    public boolean status() {
        return this.equipped;
    }
}
