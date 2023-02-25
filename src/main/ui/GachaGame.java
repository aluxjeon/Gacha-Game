package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GachaGame {
    private Scanner input;
    private final Item baseSword = new Item("Poop Sword",3,true,0);
    private final ArrayList<Characters> myCharacterInventory = new ArrayList<>();
    private final ArrayList<Item> myItemInventory = new ArrayList<>();
    private final Currency myCurrency = new Currency();
    private final CharacterGacha characterGacha = new CharacterGacha(myCharacterInventory);
    private final ItemGacha itemGacha = new ItemGacha(myItemInventory);


    public GachaGame() {
        runGachaGame();
    }

    private void runGachaGame() {
        boolean go = true;
        String command;

        initializeItems();
        initializeCharacters();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (go) {
            displayMenu();
            command = input.next();
            if (command.equals("Quit")) {
                go = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\n Logged Out");
    }

    private void initializeCharacters() {
        //TODO: Initialize Characters
        Characters character1 = new Characters("TestName1",5,true, baseSword,0);
        Characters character2 = new Characters("TestName2",5,true, baseSword,0);
        Characters character3 = new Characters("TestName3",5,true, baseSword,0);
        Characters character4 = new Characters("TestName4",5,true, baseSword,0);
        Characters character5 = new Characters("TestName5",5,true, baseSword,0);
        Characters character6 = new Characters("TestName6",4,true, baseSword,0);
        Characters character7 = new Characters("TestName7",4,true, baseSword,0);
        Characters character8 = new Characters("TestName8",4,true, baseSword,0);
        Characters character9 = new Characters("TestName9",4,true, baseSword,0);
        Characters character10 = new Characters("TestName10",4,true, baseSword,0);

        //TODO: Add 5* Characters to Roster
        characterGacha.addFiveStarCharacterRoster(character1);
        characterGacha.addFiveStarCharacterRoster(character2);
        characterGacha.addFiveStarCharacterRoster(character3);
        characterGacha.addFiveStarCharacterRoster(character4);
        characterGacha.addFiveStarCharacterRoster(character5);
        //TODO: Add 4* Characters to Roster
        characterGacha.addFourStarCharacterRoster(character6);
        characterGacha.addFourStarCharacterRoster(character7);
        characterGacha.addFourStarCharacterRoster(character8);
        characterGacha.addFourStarCharacterRoster(character9);
        characterGacha.addFourStarCharacterRoster(character10);
    }

    private void initializeItems() {
        //TODO: Initialize Items
        Item item1 = new Item("TestItem1",4,false,0);
        Item item2 = new Item("TestItem2",4,false,0);
        Item item3 = new Item("TestItem3",4,false,0);
        Item item4 = new Item("TestItem4",4,false,0);
        Item item5 = new Item("TestItem5",4,false,0);
        Item item6 = new Item("TestItem6",4,false,0);
        Item item7 = new Item("TestItem7",4,false,0);
        Item item8 = new Item("TestItem8",4,false,0);
        Item item9 = new Item("TestItem9",5,false,0);
        Item item10 = new Item("TestItem10",5,false,0);

        //TODO: Add 4* Items to Roster
        itemGacha.addFourStarItemRoster(item1);
        itemGacha.addFourStarItemRoster(item2);
        itemGacha.addFourStarItemRoster(item3);
        itemGacha.addFourStarItemRoster(item4);
        itemGacha.addFourStarItemRoster(item5);
        //TODO: Add 5* Items to Roster
        itemGacha.addFiveStarItemRoster(item6);
        itemGacha.addFiveStarItemRoster(item7);
        itemGacha.addFiveStarItemRoster(item8);
        itemGacha.addFiveStarItemRoster(item9);
        itemGacha.addFiveStarItemRoster(item10);
    }

    // EFFECTS: Display menu of options to user
    private void displayMenu() {
        System.out.println("\n Select From:");
        System.out.println("\n Gacha");
        System.out.println("\n Inventory");
        System.out.println("\n Currency");
        System.out.println("\n Quit");
    }

    // EFFECTS: Transfer to the next menu accordingly
    private void processCommand(String feedback) {
        if (feedback.equals("Gacha")) {
            gachaLoop();
        } else if (feedback.equals("Inventory")) {
            inventoryLoop();
        } else if (feedback.equals("Currency")) {
            currencyLoop();
        }
    }


    // ===================================================================================================
    // EFFECTS: Display Gacha Menu
    //TODO: Add Check pity option!
    private void gachaMenu() {
        System.out.println("\n Select From:");
        System.out.println("\n Pull Character");
        System.out.println("\n 10-Pull Character");
        System.out.println("\n Pull Item");
        System.out.println("\n 10-Pull Item");
        System.out.println("\n Show Pity");
        System.out.println("\n Back");
    }

    private void processGachaCommand(String feedBack) {
        String command = feedBack;
        if (command.equals("Pull Character") && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            characterGacha.pull();
            myCurrency.subCurrency(100);
            System.out.println("Pulled!");
        } else if (command.equals("10-Pull Character") && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            characterGacha.tenPull();
            myCurrency.subCurrency(1000);
            System.out.println("10-Pulled!");
        } else if (command.equals("Pull Item") && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            itemGacha.pull();
            myCurrency.subCurrency(100);
            System.out.println("Pulled!");
        } else if (command.equals("10-Pull Item") && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            itemGacha.tenPull();
            myCurrency.subCurrency(1000);
            System.out.println("10-Pulled!");
        } else if (command.equals("Show Pity")) {
            System.out.println("\n Character Pity: " + characterGacha.getPity());
            System.out.println("\n Item Pity: " + itemGacha.getPity());
        } else {
            System.out.println("Error");
        }
    }

    private void gachaLoop() {
        boolean go = true;
        String command;

        while (go) {
            gachaMenu();
            command = input.next();

            if (command.equals("Back")) {
                go = false;
            } else {
                processGachaCommand(command);
            }
        }
    }

    // ==============================================================================================
    // EFFECTS: Display Currency Menu
    private void currencyMenu() {
        System.out.println("\n Select From:");
        System.out.println("\n Check Currency");
        System.out.println("\n Add Currency");
        System.out.println("\n Back");
    }

    private void processCurrencyCommand(String feedBack) {
        boolean go = true;
        int amount;
        if (feedBack.equals("Check Currency")) {
            System.out.println(myCurrency.getCurrency());
        } else if (feedBack.equals("Add Currency")) {
            while (go) {
                System.out.println("\n Insert Number of Currency To Add:");
                System.out.println("\n It Takes 100 for a pull!");
                amount = input.nextInt();
                if (amount >= 0) {
                    myCurrency.addCurrency(amount);
                    go = false;
                }
            }
        }
    }

    /*
     * EFFECTS: While at the currencyMenu, if command = "Back", then stop loop & return to displayMenu
     *  If command = anything else, then run processCurrencyCommand(Command) which leads to "Check Currency"
     * or "Add Currency" model methods
    */
    private void currencyLoop() {
        String command;
        boolean go = true;

        while (go) {
            currencyMenu();
            command = input.next();
            if (command.equals("Back")) {
                go = false;
            } else {
                processCurrencyCommand(command);
            }
        }
    }

    // =======================================================================================
    // EFFECTS: Display Inventory Menu
    private void inventoryMenu() {
        System.out.println("\n Select From:");
        System.out.println("\n Show All Characters");
        System.out.println("\n Show Character Details");
        System.out.println("\n Show All Items");
        System.out.println("\n Show Item Details");
        System.out.println("\n Equip Items");
        System.out.println("\n Back");
    }

    private void processInventoryCommand(String feedBack) {
        String characterName = "";
        String itemName = "";

        if (feedBack.equals("Show All Characters")) {
            System.out.println(characterGacha.showAllCharacters());
        } else if (feedBack.equals("Show Character Details")) {
            System.out.println(characterDetailLoop());
        } else if (feedBack.equals("Show All Items")) {
            System.out.println(itemGacha.showAllItems());
        } else if (feedBack.equals("Show Item Details")) {
            System.out.println(itemDetailLoop());
            itemGacha.showItemDetails(itemName);
        } else if (feedBack.equals("Equip Items")) {
            equipLoop();
        }
    }

    private ArrayList<String> itemDetailLoop() {
        boolean go = true;
        String command;
        while (go) {
            System.out.println("Insert name of the Item you want to see: \n");
            System.out.println("Back \n");
            command = input.next();
            if (myItemInventory.contains(findItem(command))) {
                return itemGacha.showItemDetails(command);
            } else if (command.equals("Back")) {
                go = false;
            } else {
                System.out.println("You don't have this item");
            }
        }
        return null;
    }

    private ArrayList<String> characterDetailLoop() {
        boolean go = true;
        String command;
        while (go) {
            System.out.println("Insert name of the Character you want to see: \n");
            System.out.println("Back \n");
            command = input.next();
            if (myCharacterInventory.contains(findCharacter(command))) {
                return characterGacha.showCharacterDetails(command);
            } else if (command.equals("Back")) {
                go = false;
            } else {
                System.out.println("You don't have this character");
            }
        }
        return null;
    }

    private void equipLoop() {
        boolean go = true;
        String command;
        while (go) {
            System.out.println("Which Character Do You Want to Equip Items On: \n");
            System.out.println("Back \n");
            command = input.next();
            if (myCharacterInventory.contains(findCharacter(command))) {
                System.out.println("Which Item Do You Want To Equip: \n");
                System.out.println("Back \n");
                command = input.next();
                if (myItemInventory.contains(findItem(command))) {
                    findCharacter(command).equipItem(findItem(command));
                    System.out.println("\n Equipped!");
                } else {
                    System.out.println("Error");
                }
            } else if (command.equals("Back")) {
                go = false;
            } else {
                System.out.println("You don't have this character");
            }
        }
    }

    // EFFECTS: Takes a character name, if you have this character, then return
    // the Characters type of the inputted name. Else return null
    private Characters findCharacter(String name) {
        for (Characters c : myCharacterInventory) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // EFFECTS: Takes an item name, if you own this item, then return the Item type
    // of the inputted name. Else return null
    private Item findItem(String name) {
        for (Item item : myItemInventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /*
     * EFFECTS: While at the inventoryMenu, if command = "Back", then stop loop & return to displayMenu
     *  If command = anything else, then run processCurrencyCommand(Command) which deals with the behaviors
     *  of the options
     */
    private void inventoryLoop() {
        boolean go = true;
        String command;

        while (go) {
            inventoryMenu();
            command = input.next();

            if (command.equals("Back")) {
                go = false;
            } else {
                processInventoryCommand(command);
            }
        }
    }
}
