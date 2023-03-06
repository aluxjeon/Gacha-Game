package persistence;

import model.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void check(String name, int rarity, boolean equipment, Item item, int Copies, Characters character) {
        assertEquals(name, character.getName());
        assertEquals(rarity, character.getRarity());
        assertEquals(equipment, character.getEquipment());
        assertEquals(item, character.getItem());
        assertEquals(Copies, character.getCopies());
    }
}



