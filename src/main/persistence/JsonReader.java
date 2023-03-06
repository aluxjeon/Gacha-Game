package persistence;

import model.Characters;
import model.Item;
import model.WorkRoom;

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

    //TODO
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;
    private int characterPity;
    private int itemPity;
    private int currency;

    //TODO
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //TODO
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void read(ArrayList<Characters> characterList,ArrayList<Item> itemList) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseWorkRoom(jsonObject, characterList, itemList);
    }

    //TODO
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //TODO
    // EFFECTS: parses workroom from JSON object and returns it
    private void parseWorkRoom(JSONObject jsonObject, ArrayList<Characters> characterList,ArrayList<Item> itemList) {
        addCharacters(characterList,jsonObject);
        addItems(itemList,jsonObject);
        updateCharacterPity(jsonObject);
        updateItemPity(jsonObject);
        updateCurrency(jsonObject);
    }

    //TODO
    private void addCharacters(ArrayList<Characters> characterList, JSONObject jsonObject) {
        JSONArray jsonArray1 = jsonObject.getJSONArray("Characters");
        for (Object json : jsonArray1) {
            JSONObject nextThingy = (JSONObject) json;
            addCharacter(characterList, nextThingy);
        }
    }

    //TODO
    private void addItems(ArrayList<Item> itemList, JSONObject jsonObject) {
        JSONArray jsonArray2 = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray2) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(itemList, nextThingy);
        }
    }

    //TODO
    public int getCharacterPity() {
        return characterPity;
    }

    //TODO
    public int getItemPity() {
        return itemPity;
    }

    //TODO
    public int getCurrency() {
        return currency;
    }

    //TODO
    private void updateCharacterPity(JSONObject jsonObject) {
        this.characterPity = jsonObject.getInt("Character Pity");
    }

    //TODO
    private void updateItemPity(JSONObject jsonObject) {
        this.itemPity = jsonObject.getInt("Item Pity");
    }

    //TODO
    private void updateCurrency(JSONObject jsonObject) {
        this.currency = jsonObject.getInt("Currency");
    }

    //TODO
    // MODIFIES: characterList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCharacter(ArrayList<Characters> characterList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        boolean equipment = jsonObject.getBoolean("equipment");
        int copies = jsonObject.getInt("copies");
        String itemName = jsonObject.getString("item name");
        int itemRarity = jsonObject.getInt("item rarity");
        boolean itemStatus = jsonObject.getBoolean("item status");
        int itemCopies = jsonObject.getInt("item copies");
        Item item = new Item(itemName,itemRarity,itemStatus,itemCopies);
        Characters character = new Characters(name,rarity,equipment,item,copies);
        characterList.add(character);
    }

    //TODO
    // MODIFIES: itemList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addItem(ArrayList<Item> itemList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rarity = jsonObject.getInt("rarity");
        boolean equipped = jsonObject.getBoolean("equipped");
        int copies = jsonObject.getInt("copies");
        Item item = new Item(name,rarity,equipped,copies);
        itemList.add(item);
    }

}
