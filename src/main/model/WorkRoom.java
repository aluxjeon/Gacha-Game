package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/

// Represents a workroom having a list of characters and items, character & item pity, and currency
public class WorkRoom implements Writable {
    private String name;
    private int charPity;
    private int itemPity;
    private int currency;
    private List<Characters> characterThingies;
    private List<Item> itemThingies;

    // EFFECTS: constructs workroom with 'name' and empty list of characters and items
    public WorkRoom(String name) {
        this.name = name;
        characterThingies = new ArrayList<>();
        itemThingies = new ArrayList<>();
    }

    // EFFECTS: Returns name of workroom
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds character to this workroom
    public void addCharacterThingy(Characters character) {
        characterThingies.add(character);
    }

    // MODIFIES: this
    // EFFECTS: adds current currency to this workroom
    public void addCurrency(int currency) {
        this.currency = currency;
    }

    // MODIFIES: this
    // EFFECTS: adds character and item pity to this workroom
    public void addPity(int characterPity,int itemPity) {
        this.charPity = characterPity;
        this.itemPity = itemPity;
    }

    // MODIFIES: this
    // EFFECTS: adds item to this workroom
    public void addItemThingy(Item item) {
        itemThingies.add(item);
    }

    //EFFECTS: Returns a JSONObject with workroom name, JSON array of Characters, JSON array of Items,
    // characterpity, itempity, and currency
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Characters", thingiesToJson());
        json.put("Items", itemThingiesToJson());
        json.put("Character Pity", charPity);
        json.put("Item Pity", itemPity);
        json.put("Currency", currency);
        return json;
    }

    // EFFECTS: returns characters in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Characters c : characterThingies) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns items in this workroom as a JSON array
    private JSONArray itemThingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemThingies) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }

}

