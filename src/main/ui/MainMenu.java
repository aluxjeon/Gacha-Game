package ui;


import model.*;

import java.util.ArrayList;

public class MainMenu {
    private static final ArrayList<Characters> myCharacterInventory = new ArrayList<>();
    private static final ArrayList<Item> myItemInventory = new ArrayList<>();
    private static final Currency myCurrency = new Currency();
    private static final CharacterGacha characterGacha = new CharacterGacha(myCharacterInventory);
    private static final ItemGacha itemGacha = new ItemGacha(myItemInventory);

    //EFFECTS: Make new MainMenuGUI
    public static void main(String[] args) {
        MainMenuGUI mainMenuGUI = new MainMenuGUI(myCurrency,characterGacha,itemGacha);
    }

}
