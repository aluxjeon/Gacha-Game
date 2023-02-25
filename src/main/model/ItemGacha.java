package model;

import java.util.ArrayList;
import java.util.Random;

public class ItemGacha implements Collectible {
    private final ArrayList<Item> fourStarItemRoster;
    private final ArrayList<Item> fiveStarItemRoster;
    private int itemPity = 0;
    private ArrayList<Item> itemList = new ArrayList<>();

    public ItemGacha(ArrayList<Item> itemList) {
        fourStarItemRoster = new ArrayList<>();
        fiveStarItemRoster = new ArrayList<>();
        this.itemList = itemList;
    }

    public ArrayList<Item> getFourStarItemRoster() {
        return this.fourStarItemRoster;
    }

    public ArrayList<Item> getFiveStarItemRoster() {
        return this.fiveStarItemRoster;
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public int getPity() {
        return itemPity;
    }

    // EFFECTS: Add 4* item into roster for pulls
    public void addFourStarItemRoster(Item item) {
        fourStarItemRoster.add(item);
    }

    // EFFECTS: Add 5* item into roster for pulls
    public void addFiveStarItemRoster(Item item) {
        fiveStarItemRoster.add(item);
    }

    @Override
    public void pull() {
        int luck;
        int fiveStarIndex;
        int fourStarIndex;
        Random rand = new Random();
        Random fiveStarLuck = new Random();
        Random fourStarLuck = new Random();

        luck = rand.nextInt(1000); // Produce an int between [0 ~ 999]
        fiveStarIndex = fiveStarLuck.nextInt(5); // Produce an index between [0 ~ 4]
        fourStarIndex = fourStarLuck.nextInt(5); // Produce an index between [0 ~ 4]
        itemPity = itemPity + 1; // Add one to pity

        if (itemPity == 50) {
            itemPity = 0;
            System.out.println("5 Star Item! " + (fiveStarItemRoster.get(fiveStarIndex)).getName());
            itemList.add(fiveStarItemRoster.get(fiveStarIndex));
            if (!(itemList.contains(fiveStarItemRoster.get(fiveStarIndex)))) {
                itemList.add(fiveStarItemRoster.get(fiveStarIndex));
            } else {
                fiveStarItemRoster.get(fiveStarIndex).addCopies();
            }
        } else {
            System.out.println(nonPityGacha(luck, (fourStarItemRoster.get(fourStarIndex)),
                    (fiveStarItemRoster.get(fiveStarIndex))));
        }
    }

    public String nonPityGacha(int luck, Item fourStarItem,Item fiveStarItem) {
        if ((100 <= luck) && (luck <= 150)) {
            if (!(itemList.contains(fiveStarItem))) {
                itemList.add(fiveStarItem);
            } else {
                fiveStarItem.addCopies();
            }
            return ("5 Star Item! " + fiveStarItem.getName());
        } else if ((500 <= luck) && (luck <= 600)) {
            if (!(itemList.contains(fourStarItem))) {
                itemList.add(fourStarItem);
            } else {
                fourStarItem.addCopies();
            }
            return ("4 Star Item! " + fourStarItem.getName());
        } else {
            return "Poop!";
        }
    }

    //EFFECTS: Loop the pull() method 10 times
    @Override
    public void tenPull() {
        for (int i = 0;i < 10; i++) {
            pull();
        }
    }

    //EFFECTS: Shows an arraylist of all character names of the characters you own
    public ArrayList<String> showAllItems() {
        ArrayList<String> currentItemList = new ArrayList<>();
        int i = 0;
        for (Item item : itemList) {
            currentItemList.add(itemList.get(i).getName());
            i++;
        }
        return currentItemList;
    }

    //EFFECTS: Shows the name, rarity, and num of copies in arrayList
    public ArrayList<String> showItemDetails(String name) {
        int i = 0;
        ArrayList<String> itemDetails = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getName().equals(name)) {
                itemDetails.add(itemList.get(i).getName());
                String rarity = Integer.toString(itemList.get(i).getRarity());
                itemDetails.add("Rarity: " + rarity);
                String copies = Integer.toString(itemList.get(i).getCopies());
                itemDetails.add("Copies: " + copies);
            } else {
                i++;
            }
        }
        return itemDetails;
    }
}
