package persistence;

/*
Title: JsonSerializationDemo - WorkRoomApp.java
Author: Meghan Allen & Steve Wolfman [UBC]
Date: 6 March 2023
Type: Source Code
Availability: Provided for Project Phase 2 on Feb-Mar 2023 for CPSC 210.
*/

import model.Characters;
import model.Item;
import model.WorkRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    private ArrayList<Characters> characterList;
    private ArrayList<Item> itemList;
    private WorkRoom wr;
    private Item testItem;
    private Characters testCharacter;

    @BeforeEach
    void runBefore() {
        characterList = new ArrayList<>();
        itemList = new ArrayList<>();
        wr = new WorkRoom("My work room");
        testItem = new Item("Sword",5,true,0);
        testCharacter = new Characters("Alex",5,true,testItem,0);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read(characterList,itemList);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            reader.read(characterList,itemList);
            assertEquals("My work room", wr.getName());
            assertEquals(0,characterList.size());
            assertEquals(0,itemList.size());
            assertEquals(0,reader.getCurrency());
            assertEquals(0,reader.getCharacterPity());
            assertEquals(0,reader.getItemPity());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
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
            fail("Couldn't read from file");
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
