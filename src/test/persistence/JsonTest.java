package persistence;

import model.*;
import org.json.JSONObject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/
public class JsonTest {
    // EFFECTS: Checks to see if the information about character from JSONObject is correct
    // Character's item is not null
    protected void checkCharacter(String name, int rarity, boolean equipment, Item item, int copies, JSONObject json) {
        assertEquals(name, json.getString("name"));
        assertEquals(rarity, json.getInt("rarity"));
        assertEquals(equipment,json.getBoolean("equipment"));
        assertEquals(item.getName(),json.getString("item name"));
        assertEquals(item.getRarity(),json.getInt("item rarity"));
        assertEquals(item.getCopies(),json.getInt("item copies"));
        assertEquals(item.status(),json.getBoolean("item status"));
        assertEquals(copies,json.getInt("copies"));
    }

    // EFFECTS: Checks to see if the information about item from JSONObject is correct
    protected void checkItem(String name,int rarity, boolean equipped,int copies,JSONObject json) {
        assertEquals(name, json.getString("name"));
        assertEquals(rarity, json.getInt("rarity"));
        assertEquals(equipped, json.getBoolean("equipped"));
        assertEquals(copies,json.getInt("copies"));
    }

    // EFFECTS: Checks to see if the information about character from JSONObject is correct
    // Character's item is null
    protected void checkNullItemCharacters(Characters character, JSONObject json) {
        assertEquals(character.getName(),json.getString("name"));
        assertEquals(character.getRarity(),json.getInt("rarity"));
        assertEquals(character.checkItem(),json.getBoolean("equipment"));
        assertEquals("",json.getString("item name"));
        assertEquals(0,json.getInt("item rarity"));
        assertEquals(0,json.getInt("item copies"));
        assertFalse(json.getBoolean("item status"));
        assertEquals(character.getCopies(),json.getInt("copies"));
    }
}



