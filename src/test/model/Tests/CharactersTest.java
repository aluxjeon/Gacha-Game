package model.Tests;

import model.Characters;
import model.Item;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonTest;

import static org.junit.jupiter.api.Assertions.*;

public class CharactersTest extends JsonTest {
    Characters character2;
    Characters character3;
    Item item1;


    @BeforeEach
    void runBefore() {
        item1 = new Item("Sword",3,true,0);
        character2 = new Characters("Glen",4,false,null,2);
        character3 = new Characters("Apple",5,true, item1,0);
    }

    @Test
    void initializeCharactersTest() {
        Item sword1 = new Item("Base Sword",3,false,0);
        Characters character1 = new Characters("Fred",5,true,sword1,0);
        assertTrue(character1.checkItem());
        assertEquals("Fred",character1.getName());
        assertEquals(5,character1.getRarity());
        assertEquals(sword1,character1.getItem());
        assertEquals(0,character1.getCopies());
    }

    @Test
    void getNameTest() {
        assertEquals("Glen",character2.getName());
        assertEquals("Apple",character3.getName());
    }

    @Test
    void checkItemTest() {
        assertFalse(character2.checkItem());
        assertTrue(character3.checkItem());
    }

    @Test
    void getRarityTest() {
        assertEquals(4,character2.getRarity());
        assertEquals(5,character3.getRarity());
    }

    @Test
    void getItemTest() {
        assertEquals(item1,character3.getItem());
        assertNull(character2.getItem());
    }

    @Test
    void getCopiesTest() {
        assertEquals(2,character2.getCopies());
        assertEquals(0,character3.getCopies());
    }

    @Test
    void addCopiesTest() {
        assertEquals(2,character2.getCopies());
        character2.addCopies();
        assertEquals(3,character2.getCopies());

        assertEquals(0,character3.getCopies());
        character3.addCopies();
        assertEquals(1,character3.getCopies());
    }

    @Test
    void getEquipmentWhenCharacterHoldsNoItemTest() {
        assertEquals("No Equipment",character2.getEquipment());
    }

    @Test
    void getEquipmentWhenCharacterHoldsItemTest() {
        assertEquals("Equipment: Sword",character3.getEquipment());
        Item bow = new Item("Star Bow",5,false,0);
        character3.equipItem(bow);
        assertEquals("Equipment: Star Bow", character3.getEquipment());
    }

    @Test
    void equipItemWhenItemIsUnequippedAndCharacterHasNoItemTest() {
        Item bow = new Item("Star Bow",5,false,0);
        assertFalse(bow.status());
        assertFalse(character2.checkItem());
        character2.equipItem(bow);
        assertEquals("Equipment: Star Bow", character2.getEquipment());
        assertTrue(bow.status());
        assertTrue(character2.checkItem());
    }

    @Test
    void equipItemWhenItemIsEquippedAndCharacterHasNoItemTest() {
        Item bow = new Item("Star Bow",5,true,0);
        assertTrue(bow.status());
        assertFalse(character2.checkItem());
        character2.equipItem(bow);
        assertEquals("Equipment: Star Bow", character2.getEquipment());
        assertTrue(bow.status());
        assertTrue(character2.checkItem());
    }

    @Test
    void equipItemWhenItemIsUnequippedAndCharacterHasItemTest() {
        Item bow = new Item("Star Bow",5,false,0);
        assertFalse(bow.status());
        assertFalse(character2.checkItem());
        character2.equipItem(bow);
        assertEquals("Equipment: Star Bow", character2.getEquipment());
        assertTrue(bow.status());
        assertTrue(character2.checkItem());
    }

    @Test
    void equipItemWhenItemIsEquippedAndCharacterHasItemTest() {
        Item bow = new Item("Star Bow",5,true,0);
        assertTrue(bow.status());
        assertFalse(character2.checkItem());
        character2.equipItem(bow);
        assertEquals("Equipment: Star Bow", character2.getEquipment());
        assertTrue(bow.status());
        assertTrue(character2.checkItem());
    }

    @Test
    void toJsonTest() {
        checkCharacter("Apple",5,true,item1,0,character3.toJson());
        checkNullItemCharacters(character2,character2.toJson());
    }

}

