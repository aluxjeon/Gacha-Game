package model;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of thingies
public class WorkRoom implements Writable {
    private String name;
    private int charPity;
    private int itemPity;
    private int currency;
    private List<Characters> characterThingies;
    private List<Item> itemThingies;

    // EFFECTS: constructs workroom with a name and empty list of characters and items
    public WorkRoom(String name) {
        this.name = name;
        characterThingies = new ArrayList<>();
        itemThingies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds character to this workroom
    public void addThingy(Characters character) {
        characterThingies.add(character);
    }


    public void addCurrency(int currency) {
        this.currency = currency;
    }

    public void addPity(int characterPity,int itemPity) {
        this.charPity = characterPity;
        this.itemPity = itemPity;
    }

    // MODIFIES: this
    // EFFECTS: adds item to this workroom
    public void addItemThingy(Item item) {
        itemThingies.add(item);
    }

    // EFFECTS: returns an unmodifiable list of character in this workroom
    public List<Characters> getThingies() {
        return Collections.unmodifiableList(characterThingies);
    }

    // EFFECTS: returns number of character in this workroom
    public int numThingies() {
        return characterThingies.size();
    }

    // EFFECTS: returns an unmodifiable list of item in this workroom
    public List<Item> getItemThingies() {
        return Collections.unmodifiableList(itemThingies);
    }

    // EFFECTS: returns number of item in this workroom
    public int numItemThingies() {
        return itemThingies.size();
    }

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

