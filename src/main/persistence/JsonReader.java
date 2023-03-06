package persistence;

import model.Characters;
import model.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private int characterPity;
    private int itemPity;
    private int currency;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: needs arraylist inputs for characters and items
    // Reads workroom from file, then updates arraylist inputs with current characters & items
    // Also updates character and item pity & currency
    // throws IOException if an error occurs reading data from file
    public void read(ArrayList<Characters> characterList,ArrayList<Item> itemList) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        update(jsonObject, characterList, itemList);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: this, characterList, itemList
    // EFFECTS: updates character & item inventory, pities, and currency from JSON object
    private void update(JSONObject jsonObject, ArrayList<Characters> characterList, ArrayList<Item> itemList) {
        addCharacters(characterList,jsonObject);
        addItems(itemList,jsonObject);
        updateCharacterPity(jsonObject);
        updateItemPity(jsonObject);
        updateCurrency(jsonObject);
    }

    // MODIFIES: characterList
    // EFFECTS: updates character inventory by adding each character into the inventory
    private void addCharacters(ArrayList<Characters> characterList, JSONObject jsonObject) {
        JSONArray jsonArray1 = jsonObject.getJSONArray("Characters");
        for (Object json : jsonArray1) {
            JSONObject nextThingy = (JSONObject) json;
            addCharacter(characterList, nextThingy);
        }
    }

    // MODIFIES: itemList
    // EFFECTS: updates item inventory by adding each item into the inventory
    private void addItems(ArrayList<Item> itemList, JSONObject jsonObject) {
        JSONArray jsonArray2 = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray2) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(itemList, nextThingy);
        }
    }

    // EFFECTS: Returns character pity
    public int getCharacterPity() {
        return characterPity;
    }

    // EFFECTS: Returns item pity
    public int getItemPity() {
        return itemPity;
    }

    // EFFECTS: Returns currency
    public int getCurrency() {
        return currency;
    }

    // MODIFIES: this
    // EFFECTS: Updates character pity with value from JSONObject
    private void updateCharacterPity(JSONObject jsonObject) {
        this.characterPity = jsonObject.getInt("Character Pity");
    }

    // MODIFIES: this
    // EFFECTS: Updates item pity with value from JSONObject
    private void updateItemPity(JSONObject jsonObject) {
        this.itemPity = jsonObject.getInt("Item Pity");
    }

    // MODIFIES: this
    // EFFECTS: Updates currency with value from JSONObject
    private void updateCurrency(JSONObject jsonObject) {
        this.currency = jsonObject.getInt("Currency");
    }

    // MODIFIES: characterList
    // EFFECTS: gets information from each character and makes a new character object.
    // Then the character object is instantiated with the info and added to characterList
    // If the character's item's rarity is 0, then just make a new item that is null
    private void addCharacter(ArrayList<Characters> characterList, JSONObject jsonObject) {
        Item item;
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        boolean equipment = jsonObject.getBoolean("equipment");
        int copies = jsonObject.getInt("copies");
        String itemName = jsonObject.getString("item name");
        int itemRarity = jsonObject.getInt("item rarity");
        boolean itemStatus = jsonObject.getBoolean("item status");
        int itemCopies = jsonObject.getInt("item copies");
        if (itemRarity == 0) {
            item = null;
        } else {
            item = new Item(itemName,itemRarity,itemStatus,itemCopies);
        }
        Characters character = new Characters(name,rarity,equipment,item,copies);
        characterList.add(character);
    }

    // MODIFIES: itemList
    // EFFECTS: gets information from each item and makes a new item object.
    // Then the item object is instantiated with the info and added to itemList
    private void addItem(ArrayList<Item> itemList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        boolean equipped = jsonObject.getBoolean("equipped");
        int copies = jsonObject.getInt("copies");
        Item item = new Item(name,rarity,equipped,copies);
        itemList.add(item);
    }

}