package model;

public class Item {
    private String name;
    private int rarity;
    private boolean equipped;
    private int copies;


    public Item(String name, int rarity, boolean equipped,int copies) {
        this.name = name;
        this.rarity = rarity;
        this.equipped = equipped;
        this.copies = copies;
    }

    //EFFECTS: Return name of item
    public String getName() {
        return this.name;
    }

    //EFFECTS: Return rarity of item
    public int getRarity() {
        return this.rarity;
    }

    public int getCopies() {
        return this.copies;
    }

    public void addCopies() {
        this.copies++;
    }

    //TODO: equip item on character
    //TODO: change Bool from F -> T
    public void equip() {
        equipped = true;
    }

    //TODO: unequip item from character
    //TODO: change Bool from T -> F
    public void unequip() {
        equipped = false;
    }

    //TODO: Check if certain item is used or not
    public Boolean status() {
        return this.equipped;
    }
}
