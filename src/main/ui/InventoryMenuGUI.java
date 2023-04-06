package ui;

import model.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Font.PLAIN;

public class InventoryMenuGUI extends JFrame implements ActionListener {
    private final JButton backButton = new JButton();
    private final JButton weaponButton = new JButton();
    private final JButton topCharacterButton = new JButton();
    private final JButton midCharacterButton = new JButton();
    private final JButton botCharacterButton = new JButton();
    private final JButton upButton = new JButton();
    private final JButton downButton = new JButton();
    private final JButton topWeaponButton = new JButton();
    private final JButton midWeaponButton = new JButton();
    private final JButton botWeaponButton = new JButton();
    private final JButton scrollUpWeaponButton = new JButton();
    private final JButton scrollDownWeaponButton = new JButton();
    private final JButton equipButton = new JButton();

    private final JPanel itemInventoryPanel = new JPanel();
    private final JPanel idCardPanel = new JPanel();

    private final JLabel idDetailLabel = new JLabel();
    private final JLabel idRarityLabel = new JLabel();
    private final JLabel idImageLabel = new JLabel();
    private final JLabel weaponLabel = new JLabel();
    private final JLabel weaponNameLabel = new JLabel();
    private final JLabel weaponRarityLabel = new JLabel();
    private final JLabel weaponCopiesLabel = new JLabel();
    private final JLabel characterImageLabel = new JLabel();
    private final JLabel idCopiesLabel = new JLabel();

    private Boolean visible = false;
    private int count = 0;
    private int itemCount = 0;

    private final CharacterGacha characterGacha;
    private final ItemGacha itemGacha;
    private final Currency myCurrency;
    private final ArrayList<Characters> myCharacterInventory;
    private final ArrayList<Item> myItemInventory;
    private final Map<String,ImageIcon> characterPortraits = new HashMap<>();
    private final Map<String,ImageIcon> characterBigPortraits = new HashMap<>();
    private final Map<String,ImageIcon> itemPortraits = new HashMap<>();
    private final Map<String,ImageIcon> itemBigPortraits = new HashMap<>();
    private final Map<String,ImageIcon> characterFullPortraits = new HashMap<>();

    //EFFECTS: Creates new InventoryMenuGUI. Initialize characterGacha, itemGacha, myCurrency, the char &
    // item inventories based on the input parameters.Initialize character and item portraits w/ helper methods.
    public InventoryMenuGUI(Currency currency, CharacterGacha characterGacha, ItemGacha itemGacha) {
        this.characterGacha = characterGacha;
        this.itemGacha  = itemGacha;
        this.myCurrency = currency;
        this.myCharacterInventory = this.characterGacha.getCharacterList();
        this.myItemInventory = this.itemGacha.getItemList();
        initializeCharacterPortraits();
        initializeItemPortraits();
        makeInventoryMenuGui();
    }

    //MODIFIES: this
    //EFFECTS: Loops through myCharacterInventory and based on the characters you have, it gets portrait
    // images from desktop and puts it in hashmap in the form of <name of character, ImageIcon>
    private void initializeCharacterPortraits() {
        for (Characters c : this.myCharacterInventory) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + ".png");
            characterPortraits.put(c.getName(),icon);
            ImageIcon bigIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + "ID.png");
            characterBigPortraits.put(c.getName(),bigIcon);
            ImageIcon fullIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + "F.png");
            characterFullPortraits.put(c.getName(),fullIcon);
        }
    }

    //MODIFIES: this
    //EFFECTS: Loops through myItemInventory and based on the items you have, it gets portrait
    // images from desktop and puts it in hashmap in the form of <name of item, ImageIcon>
    private void initializeItemPortraits() {
        for (Item i : this.myItemInventory) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + ".png");
            itemPortraits.put(i.getName(),icon);
            ImageIcon bigIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + "Big.png");
            itemBigPortraits.put(i.getName(),bigIcon);
        }
    }

    //===================GUI=====================
    //MODIFIES: this
    //EFFECTS: Makes Inventory Menu GUI + its components
    public void makeInventoryMenuGui() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(600,400);
        this.getContentPane().setBackground(Color.white);
        itemInventoryPanel();
        backButton();
        inventoryTitleLabel();
        idCardPanel();
        characterImageLabel();
        topCharacterButton();
        midCharacterButton();
        botCharacterButton();
        upButton();
        downButton();
        scrollPanel();
        addCharacterPortraits(0);
        trackCharacterPortraits(0);
        addItemPortraits(0);
        addItemPortraits(0);
        this.setLayout(null);
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Sets up the back button in the inventory menu GUI
    private void backButton() {
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Back_Button.png");
        backButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backButton.setBorder(emptyBorder);
        backButton.setBounds(30,10,50,20);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    //MODIFIES: this
    //EFFECTS: Sets up the square image of character on ID card
    private void idImageLabel() {
        idImageLabel.setBounds(10,10,100,100);
        idCardPanel.add(idImageLabel);
    }

    //MODIFIES: this
    //EFFECTS: Sets up the weapon button of the corresponding character on ID card
    private void weaponButton() {
        weaponButton.setBounds(35,115,50,50);
        weaponButton.addActionListener(this);
        idCardPanel.add(weaponButton);
    }

    //MODIFIES: this
    //EFFECTS: In response to the weaponButton being clicked, a new GUI panel with a similar
    // layout is made with a scroll up/down button, weapon buttons, and a weapon image + card
    // 1. Initializes the weapon labels & buttons 2. Adds the labels & buttons to the panel
    // 3. Set panel visibility to false until it is changed later
    // 4. Panel is added GUI
    private void itemInventoryPanel() {
        initializeWeaponLabels();
        initializeWeaponButton();
        itemInventoryPanel.add(scrollDownWeaponButton);
        itemInventoryPanel.add(scrollUpWeaponButton);
        itemInventoryPanel.add(botWeaponButton);
        itemInventoryPanel.add(midWeaponButton);
        itemInventoryPanel.add(topWeaponButton);
        itemInventoryPanel.add(weaponNameLabel);
        itemInventoryPanel.add(weaponRarityLabel);
        itemInventoryPanel.add(weaponCopiesLabel);
        itemInventoryPanel.add(weaponLabel);
        itemInventoryPanel.add(equipButton);
        itemInventoryPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        itemInventoryPanel.setBounds(115, 70, 300, 270);
        itemInventoryPanel.setBackground(Color.gray);
        itemInventoryPanel.setOpaque(true);
        itemInventoryPanel.setVisible(false);
        itemInventoryPanel.setLayout(null);
        this.add(itemInventoryPanel);
    }

    //MODIFIES: this
    //EFFECTS: Helper method for itemInventoryPanel() to set up all the labels on the panel
    private void initializeWeaponLabels() {
        weaponLabel.setBounds(35,100,125,125);
        weaponLabel.setBackground(Color.white);
        weaponLabel.setOpaque(true);
        weaponNameLabel.setText("ITEM NAME");
        weaponNameLabel.setBounds(10,10,100,20);
        weaponRarityLabel.setText("*****");
        weaponRarityLabel.setBounds(10,30,100,20);
        weaponCopiesLabel.setText("Copies: 1");
        weaponCopiesLabel.setBounds(10,50,100,20);
    }

    //MODIFIES: this
    //EFFECTS: Helper method for itemInventoryPanel() to set up all the buttons on the panel
    private void initializeWeaponButton() {
        topWeaponButton.setBounds(200,25,75,75);
        midWeaponButton.setBounds(200,97,75,75);
        botWeaponButton.setBounds(200,170,75,75);
        scrollUpWeaponButton.setBounds(200,5,75,20);
        scrollDownWeaponButton.setBounds(200,245,75,20);
        ImageIcon upIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button.png");
        scrollUpWeaponButton.setIcon(upIcon);
        ImageIcon downIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button 2.png");
        scrollDownWeaponButton.setIcon(downIcon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollDownWeaponButton.setBorder(emptyBorder);
        scrollUpWeaponButton.setBorder(emptyBorder);
        scrollUpWeaponButton.addActionListener(this);
        scrollDownWeaponButton.addActionListener(this);
        topWeaponButton.addActionListener(this);
        midWeaponButton.addActionListener(this);
        botWeaponButton.addActionListener(this);
        equipButton.addActionListener(this);
        equipButton.setText("Equip");
        equipButton.setBounds(10,240,80,25);
    }

    //MODIFIES: this
    //EFFECTS:
    // 1. Initializes idCardPanel
    // 2.Initializes weaponButton and idImageLabel
    // 3. If there are characters in the inventory, then change the labels to the first character
    // in the inventory
    // 4. Add idCardPanel to GUI
    private void idCardPanel() {
        idCardPanel.setBackground(Color.lightGray);
        idCardPanel.setOpaque(true);
        idCardPanel.setBounds(30,100,260,175);
        idCardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        weaponButton();
        idImageLabel();
        if (myCharacterInventory.size() > 0) {
            idRarityLabel(myCharacterInventory.get(0).getRarity());
            idDetailLabel(myCharacterInventory.get(0).getName());
            idCopiesLabel(myCharacterInventory.get(0).getCopies());
            idImageLabel.setIcon(characterBigPortraits.get(myCharacterInventory.get(0).getName()));
            weaponButton.setIcon(itemPortraits.get(myCharacterInventory.get(0).getItem().getName()));
        }
        idCardPanel.setLayout(null);
        this.add(idCardPanel);
    }

    //MODIFIES: this
    //EFFECTS: if rarity is 5, then change text to "*****" OR if rarity is 4, then change text to "****"
    // ELSE, just leave an empty string. Initialize idRarityLabel & add to GUI
    private void idRarityLabel(Integer rarity) {
        if (rarity == 5) {
            idRarityLabel.setText("*****");
        } else if (rarity == 4) {
            idRarityLabel.setText("****");
        } else {
            idRarityLabel.setText("");
        }
        idRarityLabel.setFont(new Font("Lucida Handwriting",PLAIN,20));
        idRarityLabel.setBounds(120,50,50,20);
        idCardPanel.add(idRarityLabel);
    }

    //MODIFIES: this
    //EFFECTS: Sets text to the number of copies of character & initializes idCopiesLabel and adds to GUI
    private void idCopiesLabel(Integer copies) {
        idCopiesLabel.setText("Copies: " + copies);
        idCopiesLabel.setFont(new Font("Lucida Handwriting",PLAIN,15));
        idCopiesLabel.setBounds(120,75,100,20);
        idCardPanel.add(idCopiesLabel);
    }

    //MODIFIES: this
    //EFFECTS: Displays name of corresponding character & initialize idDetailLabel and add to GUI
    private void idDetailLabel(String name) {
        idDetailLabel.setText(name);
        idDetailLabel.setFont(new Font("Lucida Handwriting",PLAIN,20));
        idDetailLabel.setBounds(120,20,130,30);
        idCardPanel.add(idDetailLabel);
    }

    //MODIFIES: this
    //EFFECTS: Creates a JLabel with text of "EMPLOYEES" next to the backButton
    private void inventoryTitleLabel() {
        JLabel inventoryTitleLabel = new JLabel();
        inventoryTitleLabel.setText("EMPLOYEES");
        inventoryTitleLabel.setForeground(Color.black);
        inventoryTitleLabel.setBounds(90,10,200,20);
        inventoryTitleLabel.setFont(new Font("Lucida Handwriting",PLAIN,20));
        this.add(inventoryTitleLabel);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the characterImageLabel and adds to GUI. If there are characters in the
    // character inventory, then set the icon of characterImageLabel as the first character in inventory
    private void characterImageLabel() {
        if (myCharacterInventory.size() > 0) {
            characterImageLabel.setIcon(characterFullPortraits.get(characterGacha.getCharacterList().get(0).getName()));
        }
        characterImageLabel.setBounds(300,50,150,300);
        this.add(characterImageLabel);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the topCharacterButton and adds to GUI
    private void topCharacterButton() {
        topCharacterButton.setBounds(500,50,75,75);
        topCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        topCharacterButton.addActionListener(this);
        this.add(topCharacterButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the midCharacterButton and adds to GUI
    private void midCharacterButton() {
        midCharacterButton.setBounds(500,150,75,75);
        midCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        midCharacterButton.addActionListener(this);
        this.add(midCharacterButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the botCharacterButton and adds to GUI
    private void botCharacterButton() {
        botCharacterButton.setBounds(500,250,75,75); //75x75
        botCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        botCharacterButton.addActionListener(this);
        this.add(botCharacterButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the upButton and adds to GUI
    private void upButton() {
        upButton.setBounds(500,20,75,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button.png");
        upButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        upButton.setBorder(emptyBorder);
        upButton.addActionListener(this);
        this.add(upButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the downButton and adds to GUI
    private void downButton() {
        downButton.setBounds(500,340,75,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button 2.png");
        downButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        downButton.setBorder(emptyBorder);
        downButton.addActionListener(this);
        this.add(downButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the scrollPanel and adds to GUI
    private void scrollPanel() {
        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.gray);
        scrollPanel.setOpaque(true);
        scrollPanel.setBounds(470,0,130,400);
        this.add(scrollPanel);
    }

    //MODIFIES: this
    //EFFECTS: Performs the corresponding feedback based on which button on the GUI is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            MainMenuGUI mainMenuGUI = new MainMenuGUI(myCurrency,characterGacha,itemGacha);
        } else if (e.getSource() == upButton) {
            scrollUp();
        } else if (e.getSource() == downButton) {
            scrollDown();
        } else if (e.getSource() == botCharacterButton) {
            if (trackCharacterPortraits(count).get(2) != null) {
                setId(trackCharacterPortraits(count).get(2));
            }
        } else if (e.getSource() == midCharacterButton) {
            if (trackCharacterPortraits(count).get(1) != null) {
                setId(trackCharacterPortraits(count).get(1));
            }
        } else if (e.getSource() == topCharacterButton) {
            if (trackCharacterPortraits(count).get(0) != null) {
                setId(trackCharacterPortraits(count).get(0));
            }
        } else {
            weaponActions(e);
        }
    }

    //MODIFIES: this
    //EFFECTS: An extension of actionPerformed() that deals with all the weapon/item-related buttons
    private void weaponActions(ActionEvent e) {
        if (e.getSource() == weaponButton) {
            weaponButtonActions();
        } else if (e.getSource() == scrollUpWeaponButton) {
            scrollUpWeaponButtonAction();
        } else if (e.getSource() == scrollDownWeaponButton) {
            scrollDownWeaponButtonAction();
        } else if (e.getSource() == topWeaponButton) {
            if (trackItemPortraits(itemCount).get(0) != null) {
                setItemId(trackItemPortraits(itemCount).get(0));
            }
        } else if (e.getSource() == midWeaponButton) {
            if (trackItemPortraits(itemCount).get(1) != null) {
                setItemId(trackItemPortraits(itemCount).get(1));
            }
        } else if (e.getSource() == botWeaponButton) {
            if (trackItemPortraits(itemCount).get(2) != null) {
                setItemId(trackItemPortraits(itemCount).get(2));
            }
        } else if (e.getSource() == equipButton) {
            equipButtonActions();
        }
    }


    //MODIFIES: this
    //EFFECTS: If weaponButton is pressed, check if there are items in the inventory and if there are
    // then, set the id of the first item in the inventory and set the itemInventoryPanel to visible
    private void weaponButtonActions() {
        if (itemGacha.getItemList().size() > 0) {
            if (trackItemPortraits(itemCount).get(0) != null) {
                setItemId(trackItemPortraits(itemCount).get(0));
            }
            visible = !visible;
            itemInventoryPanel.setVisible(visible);
        }
    }

    //MODIFIES: this
    //EFFECTS: If the scrollUpWeaponButton is pressed, then if there are items with a higher index, then
    // call addItemPortraits() and trackItemPortraits() with itemCount - 3. ELSE, keep itemCount the same
    private void scrollUpWeaponButtonAction() {
        itemCount = itemCount - 3;
        if (myItemInventory.size() >= itemCount && itemCount >= 0) {
            addItemPortraits(itemCount);
            trackItemPortraits(itemCount);
        } else {
            itemCount = itemCount + 3;
        }
    }

    //MODIFIES: this
    //EFFECTS: If the scrollDownWeaponButton is pressed, then if there are more items to scroll down to,
    // then call addItemPortraits() and trackItemPortraits() with itemCount + 3. ELSE, keep itemCount the same
    private void scrollDownWeaponButtonAction() {
        itemCount = itemCount + 3;
        if (myItemInventory.size() > itemCount) {
            addItemPortraits(itemCount);
            trackItemPortraits(itemCount);
        } else {
            itemCount = itemCount - 3;
        }
    }


    //MODIFIES: this
    //EFFECTS: If equipButton is pressed, then if the item isn't already equipped, equip on character.
    // If the item is already equipped, then give a rando item to the character who is already holding
    // the item. Then, give the item to the character
    private void equipButtonActions() {
        Item item = findItem(weaponNameLabel.getText());
        if (!item.status()) {
            findCharacter(idDetailLabel.getText()).equipItem(item);
            weaponButton.setIcon(itemPortraits.get(weaponNameLabel.getText()));
        } else {
            Item randomItem = new Item("Random",3,true,0);
            findHoldItem(item).equipItem(randomItem);
            findCharacter(idDetailLabel.getText()).equipItem(item);
            weaponButton.setIcon(itemPortraits.get(weaponNameLabel.getText()));
        }
    }

    //EFFECTS: Return corresponding Characters object who holds given Item object, else return null
    private Characters findHoldItem(Item item) {
        for (Characters c : characterGacha.getCharacterList()) {
            if (c.getItem() == item) {
                return c;
            }
        }
        return null;
    }

    //EFFECTS: Return corresponding Item object based on given name, else return null
    private Item findItem(String name) {
        for (Item i : itemGacha.getItemList()) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    //EFFECTS: Return corresponding Characters object based on given name, else return null
    private Characters findCharacter(String name) {
        for (Characters c : characterGacha.getCharacterList()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: Helper method for the scroll up button. Checks if there are more characters with a lower index
    // to scroll up to and if there is, then call addCharacterPortraits() and trackCharacterPortraits()
    // starting from count - 3
    private void scrollUp() {
        count = count - 3;
        if (myCharacterInventory.size() >= count && count >= 0) {
            addCharacterPortraits(count);
            trackCharacterPortraits(count);
        } else {
            count = count + 3;
        }
    }

    //MODIFIES: this
    //EFFECTS: Helper method for the scroll down button. Checks if there are more characters
    // to scroll down to and if there is, then call addCharacterPortraits() and trackCharacterPortraits()
    // starting from count + 3
    private void scrollDown() {
        count = count + 3;
        if (myCharacterInventory.size() > count) {
            addCharacterPortraits(count);
            trackCharacterPortraits(count);
        } else {
            count = count - 3;
        }
    }

    //MODIFIES: this
    //EFFECTS: Changes the character information (name, icon, rarity, copies) on the id card panel
    // to the corresponding character
    private void setId(Characters character) {
        if (character.getName() != null) {
            idDetailLabel.setText(character.getName());
            idImageLabel.setIcon(characterBigPortraits.get(character.getName()));
            idCopiesLabel(character.getCopies());
            weaponButton.setIcon(itemPortraits.get(character.getItem().getName()));
            characterImageLabel.setIcon(characterFullPortraits.get(character.getName()));
        } else {
            System.out.println("Error");
        }
        if (character.getRarity() == 5) {
            idRarityLabel.setText("*****");
        } else if (character.getRarity() == 4) {
            idRarityLabel.setText("****");
        } else {
            System.out.println("Error");
        }
    }

    //MODIFIES: this
    //EFFECTS: Changes the item information (name, icon, rarity, copies) on the weapon panel
    // to the corresponding item
    private void setItemId(Item item) {
        if (item.getName() != null) {
            weaponNameLabel.setText(item.getName());
            weaponLabel.setIcon(itemBigPortraits.get(item.getName()));
            weaponCopiesLabel.setText("Copies: " + item.getCopies());
        } else {
            System.out.println("Error");
        }
        if (item.getRarity() == 5) {
            weaponRarityLabel.setText("*****");
        } else if (item.getRarity() == 4) {
            weaponRarityLabel.setText("****");
        } else {
            System.out.println("Error");
        }
    }

    //MODIFIES: this
    //EFFECTS: Based on the parameter start, make a new arraylist and keep track of which character
    // is in which button
    private ArrayList<Characters> trackCharacterPortraits(Integer start) {
        int i = myCharacterInventory.size() - start;
        int j = start;
        ArrayList<Characters> characterList = new ArrayList<>();
        if (!(i <= 0)) {
            characterList.add(myCharacterInventory.get(j));
        } else {
            characterList.add(null);
        }

        if (!(i - 1 <= 0)) {
            characterList.add(myCharacterInventory.get(j + 1));
        } else {
            characterList.add(null);
        }

        if (!(i - 2 <= 0)) {
            characterList.add(myCharacterInventory.get(j + 2));
        } else {
            characterList.add(null);
        }

        return characterList;
    }

    //MODIFIES: this
    //EFFECTS: Based on the parameter start, change the character buttons' icons to the corresponding
    // indices or put null if there are no more indices
    private void addCharacterPortraits(Integer start) {
        int i = myCharacterInventory.size() - start;
        int j = start;
        if (!(i <= 0)) {
            topCharacterButton.setIcon(characterPortraits.get(myCharacterInventory.get(j).getName()));
        } else {
            topCharacterButton.setIcon(null);
        }

        if (!(i - 1 <= 0)) {
            midCharacterButton.setIcon(characterPortraits.get(myCharacterInventory.get(j + 1).getName()));
        } else {
            midCharacterButton.setIcon(null);
        }

        if (!(i - 2 <= 0)) {
            botCharacterButton.setIcon(characterPortraits.get(myCharacterInventory.get(j + 2).getName()));
        } else {
            botCharacterButton.setIcon(null);
        }
    }

    //MODIFIES: this
    //EFFECTS: Based on the parameter start, make a new arraylist and keep track of which item
    // is in which button
    private ArrayList<Item> trackItemPortraits(Integer start) {
        int i = myItemInventory.size() - start;
        int j = start;
        ArrayList<Item> itemList = new ArrayList<>();
        if (!(i <= 0)) {
            itemList.add(myItemInventory.get(j));
        } else {
            itemList.add(null);
        }

        if (!(i - 1 <= 0)) {
            itemList.add(myItemInventory.get(j + 1));
        } else {
            itemList.add(null);
        }

        if (!(i - 2 <= 0)) {
            itemList.add(myItemInventory.get(j + 2));
        } else {
            itemList.add(null);
        }

        return itemList;
    }

    //MODIFIES: this
    //EFFECTS: Based on the parameter start, change the item buttons' icons to the corresponding
    // indices or put null if there are no more indices
    private void addItemPortraits(Integer start) {
        int i = myItemInventory.size() - start;
        int j = start;
        if (!(i <= 0)) {
            topWeaponButton.setIcon(itemPortraits.get(myItemInventory.get(j).getName()));
        } else {
            topWeaponButton.setIcon(null);
        }

        if (!(i - 1 <= 0)) {
            midWeaponButton.setIcon(itemPortraits.get(myItemInventory.get(j + 1).getName()));
        } else {
            midWeaponButton.setIcon(null);
        }

        if (!(i - 2 <= 0)) {
            botWeaponButton.setIcon(itemPortraits.get(myItemInventory.get(j + 2).getName()));
        } else {
            botWeaponButton.setIcon(null);
        }
    }

}
