package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a character that has a name, rarity, if the character holds an item or not, what that item is,
// and the number of copies of the character
public class Characters implements Writable {
    private final int rarity;
    private final String name;
    private boolean equipment;
    private Item item;
    private int copies;

    // EFFECTS: name of character is set to name, rarity of character is set to rarity,
    // whether the character is held by a character or not is set by equipment, the item the character holds is set
    // by item the number of copies of the character is set by copies
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
    //Else print no equipment
    public String getEquipment() {
        if (checkItem()) {
            return "Equipment: " + item.getName();
        } else {
            return "No Equipment";
        }
    }

    //MODIFIES: this.item & item & this.equipment
    //EFFECTS: if the character doesn't have an item, then changed the character to hold the item & change equipment
    // status to true. Otherwise, first change the current item the character is holding true to false
    // and then equip the item and change this new item's status to true
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rarity",rarity);
        json.put("equipment", equipment);
        json.put("item name",item.getName());
        json.put("item rarity",item.getRarity());
        json.put("item status",item.status());
        json.put("item copies",item.getCopies());
        json.put("copies",copies);
        return json;
    }
}
