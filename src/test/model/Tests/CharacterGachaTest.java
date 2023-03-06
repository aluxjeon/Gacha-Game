package model.Tests;

import model.CharacterGacha;
import model.Characters;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterGachaTest {
    private Characters character1;
    private Characters character2;
    private Characters character3;
    private Characters character4;
    private Characters character5;
    private Characters character6;
    private Characters character7;
    private Characters character8;
    private Characters character9;
    private Characters character10;
    private CharacterGacha characterGacha;
    private ArrayList<Characters> myCharacters;

    @BeforeEach
    void runBefore() {
        Item item1 = new Item("Bow",5,true,0);
        character1 = new Characters("Charlie",5,true,item1,0);
        character2 = new Characters("Alex",5,false,null,1);
        character3 = new Characters("Ray",5,false,null,2);
        character4 = new Characters("Ryan",5,false,null,1);
        character5 = new Characters("Michelle",5,false,null,0);
        character6 = new Characters("Person",4,true,item1,1);
        character7 = new Characters("Elena",4,false,null,2);
        character8 = new Characters("Eda",4,false,null,0);
        character9 = new Characters("Henry",4,false,null,1);
        character10 = new Characters("August",4,false,null,0);
        myCharacters = new ArrayList<>();
        characterGacha = new CharacterGacha(myCharacters);

    }

    @Test
    void characterGachaInitializeTest() {
        CharacterGacha otherCharacterGacha = new CharacterGacha(myCharacters);
        assertEquals(myCharacters,otherCharacterGacha.getCharacterList());
        assertEquals(emptyList(),otherCharacterGacha.getFourStarCharacterRoster());
        assertEquals(emptyList(),otherCharacterGacha.getFiveStarCharacterRoster());
    }

    @Test
    void getFourStarCharacterRosterTest() {
        assertEquals(emptyList(),characterGacha.getFourStarCharacterRoster());
        characterGacha.addFourStarCharacterRoster(character8);
        ArrayList<Characters> characterInventory = new ArrayList<>();
        characterInventory.add(character8);
        assertEquals(characterInventory,characterGacha.getFourStarCharacterRoster());
    }

    @Test
    void getFiveStarCharacterRosterTest() {
        assertEquals(emptyList(),characterGacha.getFiveStarCharacterRoster());
        characterGacha.addFiveStarCharacterRoster(character3);
        ArrayList<Characters> characterInventory = new ArrayList<>();
        characterInventory.add(character3);
        assertEquals(characterInventory,characterGacha.getFiveStarCharacterRoster());
    }

    @Test
    void getCharacterList() {
        assertEquals(myCharacters,characterGacha.getCharacterList());
    }

    @Test
    void addFourStarCharacterRosterTest() {
        assertEquals(emptyList(),characterGacha.getFourStarCharacterRoster());
        characterGacha.addFourStarCharacterRoster(character6);
        characterGacha.addFourStarCharacterRoster(character7);
        ArrayList<Characters> characterInventory = new ArrayList<>();
        characterInventory.add(character6);
        characterInventory.add(character7);
        assertEquals(characterInventory,characterGacha.getFourStarCharacterRoster());
    }

    @Test
    void addFiveStarCharacterRosterTest() {
        assertEquals(emptyList(),characterGacha.getFiveStarCharacterRoster());
        characterGacha.addFiveStarCharacterRoster(character1);
        characterGacha.addFiveStarCharacterRoster(character2);
        ArrayList<Characters> characterInventory = new ArrayList<>();
        characterInventory.add(character1);
        characterInventory.add(character2);
        assertEquals(characterInventory,characterGacha.getFiveStarCharacterRoster());
    }

    @Test
    void getPityTest() {
        initialize();
        assertEquals(0,characterGacha.getPity());
        characterGacha.pull();
        assertEquals(1,characterGacha.getPity());
        characterGacha.tenPull();
        assertEquals(11,characterGacha.getPity());
    }

    @Test
    void pullNonPityGachaTest() {
        initialize();
        assertEquals(0,characterGacha.getPity());
        assertEquals(0,characterGacha.getCharacterList().size());
        characterGacha.pull();
        characterGacha.pull();
        characterGacha.pull();
        assertEquals(3,characterGacha.getPity());
        characterGacha.pull();
        assertEquals(4,characterGacha.getPity());
    }

    @Test
    void tenPullNonPityGachaTest() {
        initialize();
        assertEquals(0,characterGacha.getPity());
        assertEquals(0,characterGacha.getCharacterList().size());
        characterGacha.tenPull();
        assertEquals(10,characterGacha.getPity());
        characterGacha.tenPull();
        assertEquals(20,characterGacha.getPity());
    }


    @Test
    void nonPityGachaTest() {
        assertEquals("5 Star Character! Charlie",characterGacha.nonPityGacha(100,character6, character1));
        assertEquals("5 Star Character! Charlie",characterGacha.nonPityGacha(110,character6, character1));
        int testCopies = 0;
        for (Characters c : myCharacters) {
            if (c.getName().equals("Charlie")) {
                testCopies = c.getCopies();
            }
        }
        assertEquals(1,testCopies);
        assertEquals("4 Star Character! Person",characterGacha.nonPityGacha(550,character6, character1));
        assertEquals("5 Star Character! Alex",characterGacha.nonPityGacha(125,character7, character2));
        assertEquals("4 Star Character! Elena",characterGacha.nonPityGacha(566,character7, character2));
        assertEquals("Poop!",characterGacha.nonPityGacha(0,character6, character1));
        assertEquals("Poop!",characterGacha.nonPityGacha(5,character7, character2));
    }

    @Test
    void pullAtPityTest() {
        initialize();
        for (int i = 0;i<49;i++) {
            characterGacha.pull();
        }
        myCharacters.clear();
        characterGacha.pull();
        int rarityTest = myCharacters.get(0).getRarity();
        assertEquals(5,rarityTest);
    }

    @Test
    void pullAtPityCheckAddedCopiesTest() {
        boolean b = false;
        initialize();
        for (int i = 0; i < 49; i++) {
            characterGacha.pull();
        }
        myCharacters.clear();
        myCharacters.add(character1);
        myCharacters.add(character2);
        myCharacters.add(character3);
        myCharacters.add(character4);
        myCharacters.add(character5);
        characterGacha.pull();
        for (Characters c : myCharacters) {
            if (c.getCopies() == 1) {
                b = true;
            }
        }
        assertTrue(b);
    }

    @Test
    void tenPullAtPityTest() {
        initialize();
        for (int i = 0;i<4;i++) {
            characterGacha.tenPull();
        }
        assertEquals(40,characterGacha.getPity());
        myCharacters.clear();
        characterGacha.tenPull();
        ArrayList<Integer> rarityList = new ArrayList<>();
        int sum = 0;

        for (Characters c : myCharacters) {
            rarityList.add(c.getRarity());
        }
        for (int i : rarityList) {
            if (i == 5) {
                sum += 5;
            }
        }
        assertTrue(sum >= 5);
        assertEquals(0,characterGacha.getPity());
    }

    @Test
    void tenPullOverPityTest() {
        initialize();
        for (int i = 0;i<4;i++) {
            characterGacha.tenPull();
        }
        characterGacha.pull();
        assertEquals(41,characterGacha.getPity());
        myCharacters.clear();
        characterGacha.tenPull();
        ArrayList<Integer> rarityList = new ArrayList<>();
        int sum = 0;

        for (Characters c : myCharacters) {
            rarityList.add(c.getRarity());
        }
        for (Integer i : rarityList) {
            if (i == 5) {
                sum += 5;
            }
        }
        assertTrue(sum >= 5);
        assertEquals(1,characterGacha.getPity());
    }

    @Test
    void showAllCharactersTest() {
        ArrayList<String> currentCharacterList = new ArrayList<>();
        for (Characters c : myCharacters) {
            currentCharacterList.add(c.getName());
        }
        assertEquals(currentCharacterList,characterGacha.showAllCharacters());
        ArrayList<Characters> yourCharacters = new ArrayList<>();
        ArrayList<String> yourCharactersNames = new ArrayList<>();
        yourCharacters.add(character1);
        yourCharacters.add(character2);
        yourCharacters.add(character6);
        yourCharacters.add(character9);
        for (Characters c : yourCharacters) {
            yourCharactersNames.add(c.getName());
        }
        CharacterGacha otherCharacterGacha = new CharacterGacha(yourCharacters);
        assertEquals(yourCharactersNames,otherCharacterGacha.showAllCharacters());
    }

    @Test
    void showCharacterDetailsTest() {
        ArrayList<String> charlieList = new ArrayList<>();
        charlieList.add("Charlie");
        charlieList.add("Rarity: 5");
        charlieList.add("Equipment: Bow");
        charlieList.add("Copies: 0");
        myCharacters.add(character1);
        assertEquals(charlieList,characterGacha.showCharacterDetails("Charlie"));
        myCharacters.clear();
        myCharacters.add(character2);
        myCharacters.add(character7);
        myCharacters.add(character10);
        myCharacters.add(character1);
        assertEquals(charlieList,characterGacha.showCharacterDetails("Charlie"));
    }

    @Test
    void showNoCharacterDetailsTest() {
        ArrayList<String> nullList = new ArrayList<>();
        assertEquals(nullList,characterGacha.showCharacterDetails("Charlie"));
    }

    private void initialize() {
        characterGacha.addFiveStarCharacterRoster(character1);
        characterGacha.addFiveStarCharacterRoster(character2);
        characterGacha.addFiveStarCharacterRoster(character3);
        characterGacha.addFiveStarCharacterRoster(character4);
        characterGacha.addFiveStarCharacterRoster(character5);
        characterGacha.addFourStarCharacterRoster(character6);
        characterGacha.addFourStarCharacterRoster(character7);
        characterGacha.addFourStarCharacterRoster(character8);
        characterGacha.addFourStarCharacterRoster(character9);
        characterGacha.addFourStarCharacterRoster(character10);
    }
}
