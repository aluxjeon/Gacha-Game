package model;

public class Characters {
    private final int rarity;
    private final String name;
    private boolean equipment;
    private Item item;
    private int copies;

    // I would want to also take an image for art of character later
    public Characters(String name, int rarity, boolean equipment, Item item, int copies) {
        this.name = name;
        this.rarity = rarity;
        this.equipment = equipment;
        this.item = item;
        this.copies = copies;
    }

    //EFFECTS: Returns true if character holds item. Else return false
    public Boolean checkItem() {
        return this.equipment;
    }

    //EFFECTS: Returns Item if character holds item (checkItem() == true), Else return null
    public Item getItem() {
        return this.item;
    }

    //EFFECTS: Return name of character
    public String getName() {
        return this.name;
    }

    //EFFECTS: Return rarity of character
    public int getRarity() {
        return this.rarity;
    }

    //EFFECTS: Return copies of character
    public int getCopies() {
        return this.copies;
    }

    //EFFECTS: Add 1 to character's copies
    public void addCopies() {
        copies++;
    }

    //EFFECTS: If character is holding equipment, print statement of what item character is holding.
    //Else print "Character is not holding anything"
    public String getEquipment() {
        if (checkItem()) {
            return "Equipment: " + item.getName();
        } else {
            return "No Equipment";
        }
    }

    public void equipItem(Item item) {
        if (!this.checkItem()) {
            this.item = item;
            this.equipment = true;
            item.equip();
        } else {
            this.item.unequip();
            this.item = item;
            item.equip();
        }
    }
}
