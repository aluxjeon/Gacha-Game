package persistence;

import model.*;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/
public class JsonTest {
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

    protected void checkItem(String name,int rarity, boolean equipped,int copies,JSONObject json) {
        assertEquals(name, json.getString("name"));
        assertEquals(rarity, json.getInt("rarity"));
        assertEquals(equipped, json.getBoolean("equipped"));
        assertEquals(copies,json.getInt("copies"));
    }
}



