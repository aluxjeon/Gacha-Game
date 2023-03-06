package model.Tests;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    Item item1;
    Item item2;

    @BeforeEach
    void runBefore() {
        item1 = new Item("Sword",5,false,0);
        item2 = new Item("Bow",4,true,1);
    }

    @Test
    void getNameTest() {
        assertEquals("Sword",item1.getName());
    }

    @Test
    void getRarityTest() {
        assertEquals(5,item1.getRarity());
    }

    @Test
    void getCopiesTest() {
        assertEquals(0,item1.getCopies());
    }

    @Test
    void addCopiesTest() {
        assertEquals(0,item1.getCopies());
        item1.addCopies();
        assertEquals(1,item1.getCopies());
    }

    @Test
    void equipTest() {
        assertFalse(item1.status());
        item1.equip();
        assertTrue(item1.status());

        assertTrue(item2.status());
        item2.equip();
        assertTrue(item2.status());
    }

    @Test
    void unequipTest() {
        assertTrue(item2.status());
        item2.unequip();
        assertFalse(item2.status());

        assertFalse(item1.status());
        item1.unequip();
        assertFalse(item1.status());
    }

    @Test
    void statusTest() {
        assertFalse(item1.status());
        assertTrue(item2.status());
    }
}
