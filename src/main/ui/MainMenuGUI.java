package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Font.PLAIN;

public class MainMenuGUI extends JFrame implements ActionListener, WindowListener, WindowFocusListener,
        WindowStateListener {
    private final Item baseSword = new Item("Poop Sword",3,true,0);
    private final ArrayList<Characters> myCharacterInventory;
    private final ArrayList<Item> myItemInventory;
    private final Currency myCurrency;
    private final CharacterGacha characterGacha;
    private final ItemGacha itemGacha;
    private static final String JSON_STORE = "./data/workroom.json";
    private final WorkRoom workRoom;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private final JButton hireButton = new JButton();
    private final JButton inventoryButton = new JButton();
    private final JButton saveButton = new JButton();
    private final JButton loadButton = new JButton();

    //Makes new MainMenuGUI. Initializes myCurrency, characterGacha, itemGacha, myCharacterInventory,
    // myItemInventory based on given parameters. Initialize the items and characters into rosters for
    // characterGacha and itemGacha. Makes new workroom, jsonwriter, jsonreader. Makes main page GUI.
    public MainMenuGUI(Currency currency,CharacterGacha characterGacha,ItemGacha itemGacha) {
        this.myCurrency = currency;
        this.characterGacha = characterGacha;
        this.itemGacha = itemGacha;
        this.myCharacterInventory = this.characterGacha.getCharacterList();
        this.myItemInventory = this.itemGacha.getItemList();
        initializeItems();
        initializeCharacters();
        workRoom = new WorkRoom("Alex's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        makeMainPageGUI();
    }


    //====================GUI==========================
    //MODIFIES: this
    //EFFECTS: Makes main page GUI and initializes its GUI components with helper
    // methods
    public void makeMainPageGUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(600,400);
        this.getContentPane().setBackground(Color.white);
        hireButton();
        inventoryButton();
        saveButton();
        loadButton();
        gameTitleLabel();
        buildingImagePanel();
        this.setLayout(null);
        this.setVisible(true);
        addWindowListener(this);
        addWindowFocusListener(this);
        addWindowStateListener(this);
    }


    //===================GUI Components==================
    //MODIFIES: this
    //EFFECTS: Initialize hireButton & add to GUI
    private void hireButton() {
        hireButton.setBackground(Color.green);
        hireButton.setOpaque(true);
        hireButton.setText("HIRE");
        hireButton.setFont(new Font("Lucida Handwriting",PLAIN,25));
        hireButton.setBounds(300,60,250,100);
        hireButton.addActionListener(this);
        this.add(hireButton);
    }

    //MODIFIES: this
    //EFFECTS: Initialize inventoryButton & add to GUI
    private void inventoryButton() {
        inventoryButton.setBackground(Color.green);
        inventoryButton.setOpaque(true);
        inventoryButton.setText("EMPLOYEES");
        inventoryButton.setFont(new Font("Lucida Handwriting",PLAIN,25));
        inventoryButton.setBounds(300,170,250,100);
        inventoryButton.addActionListener(this);
        this.add(inventoryButton);
    }

    //MODIFIES: this
    //EFFECTS: Initialize saveButton & add to GUI
    private void saveButton() {
        saveButton.setBackground(Color.blue);
        saveButton.setOpaque(true);
        saveButton.setText("SAVE");
        saveButton.setFont(new Font("Lucida Handwriting",PLAIN,25));
        saveButton.setBounds(300,280,115,75);
        saveButton.addActionListener(this);
        this.add(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: Initialize loadButton & add to GUI
    private void loadButton() {
        loadButton.setBackground(Color.blue);
        loadButton.setOpaque(true);
        loadButton.setText("LOAD");
        loadButton.setFont(new Font("Lucida Handwriting",PLAIN,25));
        loadButton.setBounds(435,280,115,75);
        loadButton.addActionListener(this);
        this.add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: Initialize/Creates gameTitleLabel & add to GUI
    private void gameTitleLabel() {
        JLabel gameTitleLabel = new JLabel();
        gameTitleLabel.setText("Company Inc. Game");
        gameTitleLabel.setFont(new Font("Lucida Handwriting",PLAIN,25));
        gameTitleLabel.setForeground(Color.black);
        gameTitleLabel.setBounds(30,20,300,30);
        this.add(gameTitleLabel);
    }

    //MODIFIES: this
    //EFFECTS: Initialize/Creates buildingImagePanel & add to GUI
    private void buildingImagePanel() {
        JPanel buildingImagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("src/images/building.png");
        JLabel buildingImage = new JLabel();
        buildingImage.setBounds(0,0,200,300);
        buildingImage.setIcon(icon);
        buildingImagePanel.add(buildingImage);
        buildingImagePanel.setBackground(Color.gray);
        buildingImagePanel.setOpaque(true);
        buildingImagePanel.setBounds(50,60,200,300);
        buildingImagePanel.setLayout(null);
        this.add(buildingImagePanel);
    }


    //===================ACTION EVENTS==================
    //MODIFIES: this
    //EFFECTS: Performs the corresponding feedback based on which button on the GUI is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadButton) {
            loadWorkRoom();
            System.out.println("LOAD");
        } else if (e.getSource() == saveButton) {
            saveWorkRoom();
            System.out.println("SAVE");
        } else if (e.getSource() == inventoryButton) {
            this.dispose();
            InventoryMenuGUI inventoryMenuGUI = new InventoryMenuGUI(myCurrency,characterGacha,itemGacha);
            System.out.println("INVENTORY");
        } else if (e.getSource() == hireButton) {
            this.dispose();
            GachaMenuGUI gachaMenuGUI = new GachaMenuGUI(myCurrency,characterGacha,itemGacha);
            System.out.println("HIRE");
        }
    }


    //======================INITIALIZE====================

    //MODIFIES: this
    //EFFECTS: 1. Sets all the possible characters you can pull for. Each character initially has the item baseSword and
    //            has 0 copies
    //         2. Adds 5* & 4* to their respective roster so we can pull for them randomly
    private void initializeCharacters() {
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

    //MODIFIES: this
    //EFFECTS: 1. Sets all the possible items you can pull for. Each items initially has the status of false and
    //            has 0 copies
    //         2. Adds 5* & 4* to their respective roster so we can pull for them randomly
    private void initializeItems() {
        Item item1 = new Item("TestItem1",4,false,0);
        Item item2 = new Item("TestItem2",4,false,0);
        Item item3 = new Item("TestItem3",4,false,0);
        Item item4 = new Item("TestItem4",4,false,0);
        Item item5 = new Item("TestItem5",4,false,0);
        Item item6 = new Item("TestItem6",5,false,0);
        Item item7 = new Item("TestItem7",5,false,0);
        Item item8 = new Item("TestItem8",5,false,0);
        Item item9 = new Item("TestItem9",5,false,0);
        Item item10 = new Item("TestItem10",5,false,0);

        itemGacha.addFourStarItemRoster(item1);
        itemGacha.addFourStarItemRoster(item2);
        itemGacha.addFourStarItemRoster(item3);
        itemGacha.addFourStarItemRoster(item4);
        itemGacha.addFourStarItemRoster(item5);
        itemGacha.addFiveStarItemRoster(item6);
        itemGacha.addFiveStarItemRoster(item7);
        itemGacha.addFiveStarItemRoster(item8);
        itemGacha.addFiveStarItemRoster(item9);
        itemGacha.addFiveStarItemRoster(item10);
    }

    //=======================SAVE / LOAD FUNCTIONS========================

    //MODIFIES: this
    //EFFECTS: 1. Run addWorkRoomElement()
    //         2. Run jsonWriter
    //         3. Print out where it is saved to
    // If the file cannot be read, then print error and catch FileNotFoundException
    private void saveWorkRoom() {
        try {
            addWorkRoomElement();
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: 1. Read file with jsonReader.read()
    //         2. Update character pity and item pity and currency
    //         3. Print out where the JSONObject is loaded from
    // If the file cannot be read, then print error and catch IOException
    private void loadWorkRoom() {
        try {
            jsonReader.read(myCharacterInventory,myItemInventory);
            characterGacha.addPity(jsonReader.getCharacterPity());
            itemGacha.addPity(jsonReader.getItemPity());
            myCurrency.subCurrency(myCurrency.getCurrency());
            myCurrency.addCurrency(jsonReader.getCurrency());
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Add each character and item from workroom to character/item inventory
    private void addWorkRoomElement() {
        for (Characters c : myCharacterInventory) {
            workRoom.addCharacterThingy(c);
        }
        for (Item i : myItemInventory) {
            workRoom.addItemThingy(i);
        }
        workRoom.addCurrency(myCurrency.getCurrency());
        workRoom.addPity(characterGacha.getPity(), itemGacha.getPity());
    }


    //=========================WINDOW LISTENER==========================

    //EFFECTS: Nothing
    @Override
    public void windowGainedFocus(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowLostFocus(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowOpened(WindowEvent e) {}

    //EFFECTS: When the window is closed, print out event logs to console
    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("\n" + "=========Event Logs=========" + "\n");
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
        System.out.println("=========Event Logs=========" + "\n");
    }

    //EFFECTS: Nothing
    @Override
    public void windowClosed(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowIconified(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowDeiconified(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowActivated(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowDeactivated(WindowEvent e) {}

    //EFFECTS: Nothing
    @Override
    public void windowStateChanged(WindowEvent e) {}

}
