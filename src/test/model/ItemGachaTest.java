package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static java.util.Collections.emptyList;


public class ItemGachaTest {
    private ItemGacha itemGacha;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;
    private Item item6;
    private Item item7;
    private Item item8;
    private Item item9;
    private Item item10;
    private Characters character1;
    private ArrayList<Item> myItems = new ArrayList<>();


    @BeforeEach
    void runBefore() {
        item1 = new Item("Bow",5,false,0);
        item2 = new Item("Sword", 5,true,1);
        item3 = new Item("Stick", 5,true,12);
        item4 = new Item("Polearm", 5,false,11);
        item5 = new Item("Paperclip", 5,true,1);
        item6 = new Item("Rock", 4,false,10);
        item7 = new Item("Lance", 4,false,1);
        item8 = new Item("Pole", 4,true,1);
        item9 = new Item("Slingshot", 4,false,2);
        item10 = new Item("Bad Sword", 4,false,4);
        itemGacha = new ItemGacha(myItems);
    }

    @Test
    void itemGachaInitializeTest() {
        ItemGacha otherItemGacha = new ItemGacha(myItems);
        assertEquals(myItems,otherItemGacha.getItemList());
        assertEquals(emptyList(), otherItemGacha.getFourStarItemRoster());
        assertEquals(emptyList(), otherItemGacha.getFiveStarItemRoster());
    }

    @Test
    void getFourStarItemRosterTest() {
        assertEquals(emptyList(),itemGacha.getFourStarItemRoster());
        itemGacha.addFourStarItemRoster(item6);
        ArrayList<Item> itemInventory = new ArrayList<>();
        itemInventory.add(item6);
        assertEquals(itemInventory,itemGacha.getFourStarItemRoster());
    }

    @Test
    void getFiveStarItemRosterTest() {
        assertEquals(emptyList(),itemGacha.getFiveStarItemRoster());
        itemGacha.addFiveStarItemRoster(item1);
        ArrayList<Item> itemInventory = new ArrayList<>();
        itemInventory.add(item1);
        assertEquals(itemInventory,itemGacha.getFiveStarItemRoster());
    }

    @Test
    void getItemListTest() {
        assertEquals(myItems,itemGacha.getItemList());
    }

    @Test
    void getPityTest() {
        initialize();
        assertEquals(0,itemGacha.getPity());
        itemGacha.pull();
        assertEquals(1,itemGacha.getPity());
        itemGacha.tenPull();
        assertEquals(11,itemGacha.getPity());
    }

    @Test
    void addFourStarItemRosterTest() {
        assertEquals(emptyList(),itemGacha.getFourStarItemRoster());
        itemGacha.addFourStarItemRoster(item6);
        itemGacha.addFourStarItemRoster(item7);
        ArrayList<Item> itemInventory = new ArrayList<>();
        itemInventory.add(item6);
        itemInventory.add(item7);
        assertEquals(itemInventory,itemGacha.getFourStarItemRoster());
    }

    @Test
    void addFiveStarItemRosterTest() {
        assertEquals(emptyList(),itemGacha.getFiveStarItemRoster());
        itemGacha.addFiveStarItemRoster(item1);
        itemGacha.addFiveStarItemRoster(item2);
        ArrayList<Item> itemInventory = new ArrayList<>();
        itemInventory.add(item1);
        itemInventory.add(item2);
        assertEquals(itemInventory,itemGacha.getFiveStarItemRoster());
    }

    @Test
    void pullNonPityGachaTest() {
        initialize();
        assertEquals(0,itemGacha.getPity());
        assertEquals(0,itemGacha.getItemList().size());
        itemGacha.pull();
        assertEquals(1,itemGacha.getPity());
        assertTrue(itemGacha.getItemList().size() >= 0);
        itemGacha.pull();
        assertEquals(2,itemGacha.getPity());
        assertTrue(itemGacha.getItemList().size() >= 0);
    }

    @Test
    void tenPullNonPityGachaTest() {
        initialize();
        assertEquals(0,itemGacha.getPity());
        assertEquals(0,itemGacha.getItemList().size());
        itemGacha.tenPull();
        assertEquals(10,itemGacha.getPity());
        assertTrue(itemGacha.getItemList().size() >= 0);
        itemGacha.tenPull();
        assertEquals(20,itemGacha.getPity());
        assertTrue(itemGacha.getItemList().size() >= 0);
    }

    @Test
    void nonPityGachaTest() {
        assertEquals("5 Star Item! Bow",itemGacha.nonPityGacha(100,item6, item1));
        assertEquals("4 Star Item! Rock",itemGacha.nonPityGacha(550,item6, item1));
        assertEquals("5 Star Item! Sword",itemGacha.nonPityGacha(125,item7, item2));
        assertEquals("4 Star Item! Lance",itemGacha.nonPityGacha(566,item7, item2));
        assertEquals("Poop!",itemGacha.nonPityGacha(0,item6, item1));
        assertEquals("Poop!",itemGacha.nonPityGacha(5,item7, item2));
    }

    @Test
    void pullAtPityTest() {
        initialize();
        for (int i = 0;i<49;i++) {
            itemGacha.pull();
        }
        myItems.clear();
        itemGacha.pull();
        int rarityTest = myItems.get(0).getRarity();
        assertEquals(5,rarityTest);
    }

    @Test
    void tenPullAtPityTest() {
        initialize();
        for (int i = 0;i<4;i++) {
            itemGacha.tenPull();
        }
        assertEquals(40,itemGacha.getPity());
        myItems.clear();
        itemGacha.tenPull();
        ArrayList<Integer> rarityList = new ArrayList<>();
        Integer sum = 0;

        for (Item i : myItems) {
            rarityList.add(i.getRarity());
        }
        for (Integer i : rarityList) {
            if (i == 5) {
                sum += 5;
            }
        }
        assertTrue(sum >= 5);
        assertEquals(0,itemGacha.getPity());
    }

    @Test
    void tenPullOverPityTest() {
        initialize();
        for (int i = 0;i<4;i++) {
            itemGacha.tenPull();
        }
        itemGacha.pull();
        assertEquals(41,itemGacha.getPity());
        myItems.clear();
        itemGacha.tenPull();
        ArrayList<Integer> rarityList = new ArrayList<>();
        Integer sum = 0;

        for (Item i : myItems) {
            rarityList.add(i.getRarity());
        }
        for (Integer i : rarityList) {
            if (i == 5) {
                sum += 5;
            }
        }
        assertTrue(sum >= 5);
        assertEquals(1,itemGacha.getPity());
    }

    @Test
    void showAllItemsTest() {
        ArrayList<String> currentItemList = new ArrayList<>();
        for (Item i : myItems) {
            currentItemList.add(i.getName());
        }
        assertEquals(currentItemList,itemGacha.showAllItems());
    }

    @Test
    void showItemDetailsTest() {
        ArrayList<String> bowList = new ArrayList<>();
        bowList.add("Bow");
        bowList.add("Rarity: 5");
        bowList.add("Copies: 0");
        myItems.add(item1);
        assertEquals(bowList,itemGacha.showItemDetails("Bow"));
    }

    @Test
    void sowNoItemDetailsTest() {
        ArrayList<String> nullList = new ArrayList<>();
        assertEquals(nullList, itemGacha.showItemDetails("Bow"));
    }

    private void initialize() {
        itemGacha.addFiveStarItemRoster(item1);
        itemGacha.addFiveStarItemRoster(item2);
        itemGacha.addFiveStarItemRoster(item3);
        itemGacha.addFiveStarItemRoster(item4);
        itemGacha.addFiveStarItemRoster(item5);
        itemGacha.addFourStarItemRoster(item6);
        itemGacha.addFourStarItemRoster(item7);
        itemGacha.addFourStarItemRoster(item8);
        itemGacha.addFourStarItemRoster(item9);
        itemGacha.addFourStarItemRoster(item10);
    }

}
