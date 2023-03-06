package persistence;

import model.Characters;
import model.Item;
import model.WorkRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/

public class JsonWriterTest extends JsonTest {
    private ArrayList<Characters> characterList;
    private ArrayList<Item> itemList;
    private Item testItem;
    private Characters testCharacter;

    @BeforeEach
    void runBefore() {
        testItem = new Item("Sword",5,true,0);
        testCharacter = new Characters("Alex",5,true,testItem,0);
        itemList = new ArrayList<>();
        characterList = new ArrayList<>();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            reader.read(characterList,itemList);
            assertEquals("My work room", wr.getName());
            assertEquals(0,characterList.size());
            assertEquals(0,itemList.size());
            assertEquals(0,reader.getCurrency());
            assertEquals(0,reader.getCharacterPity());
            assertEquals(0,reader.getItemPity());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            wr.addCharacterThingy(testCharacter);
            wr.addItemThingy(testItem);
            wr.addPity(10,20);
            wr.addCurrency(1000);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            reader.read(characterList,itemList);
            assertEquals("My work room", wr.getName());
            assertEquals(1,characterList.size());
            checkCharacters(testCharacter,characterList);
            assertEquals(1,itemList.size());
            checkItem(testItem,itemList);
            assertEquals(1000,reader.getCurrency());
            assertEquals(10,reader.getCharacterPity());
            assertEquals(20,reader.getItemPity());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private void checkCharacters(Characters character,ArrayList<Characters> characters) {
        assertEquals(character.getName(),characters.get(0).getName());
        assertEquals(character.getRarity(),characters.get(0).getRarity());
        assertEquals(character.getItem().getName(),characters.get(0).getItem().getName());
        assertEquals(character.getItem().getCopies(),characters.get(0).getItem().getCopies());
        assertEquals(character.getItem().getRarity(),characters.get(0).getItem().getRarity());
        assertEquals(character.getItem().status(),characters.get(0).getItem().status());
        assertEquals(character.getCopies(),characters.get(0).getCopies());
    }

    private void checkItem(Item item, ArrayList<Item> items) {
        assertEquals(item.getName(),items.get(0).getName());
        assertEquals(item.getCopies(),items.get(0).getCopies());
        assertEquals(item.getRarity(),items.get(0).getRarity());
        assertEquals(item.status(),items.get(0).status());
    }

}
