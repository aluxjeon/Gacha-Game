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
    private final JPanel idCardPanel = new JPanel();
    private int count = 0;
    private final JLabel idDetailLabel = new JLabel();
    private final JLabel idRarityLabel = new JLabel();
    private final JLabel idImageLabel = new JLabel();
    private final JPanel itemInventoryPanel = new JPanel();
    private Boolean visible = false;

    private final CharacterGacha characterGacha;
    private final ItemGacha itemGacha;
    private final Currency myCurrency;
    private final ArrayList<Characters> myCharacterInventory;
    private final ArrayList<Item> myItemInventory;
    private final Map<String,ImageIcon> characterPortraits = new HashMap<String,ImageIcon>();
    private final Map<String,ImageIcon> characterBigPortraits = new HashMap<String,ImageIcon>();
    private final Map<String,ImageIcon> itemPortraits = new HashMap<String,ImageIcon>();

    public InventoryMenuGUI(Currency currency, CharacterGacha characterGacha, ItemGacha itemGacha) {
        this.characterGacha = characterGacha;
        this.itemGacha  = itemGacha;
        this.myCurrency = currency;
        this.myCharacterInventory = this.characterGacha.getCharacterList();
        this.myItemInventory = this.itemGacha.getItemList();

        for (Characters c : this.myCharacterInventory) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + ".png");
            characterPortraits.put(c.getName(),icon);
            ImageIcon bigIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + "ID.png");
            characterBigPortraits.put(c.getName(),bigIcon);
        }

        for (Item i : this.myItemInventory) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + ".png");
            itemPortraits.put(i.getName(),icon);
        }

        makeInventoryMenuGui();
    }

    //===================GUI=====================
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


        this.setLayout(null);
        this.setVisible(true);
    }

    private void backButton() {
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Back_Button.png");
        backButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backButton.setBorder(emptyBorder);
        backButton.setBounds(30,10,50,20);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    private void idImageLabel() {
        idImageLabel.setBounds(10,10,100,100); //100X100
        idCardPanel.add(idImageLabel);
    }

    private void weaponButton() {
        weaponButton.setBounds(35,115,50,50);
        weaponButton.addActionListener(this);
        idCardPanel.add(weaponButton);
    }

    private void itemInventoryPanel() {
        itemInventoryPanel.setBounds(115, 75, 300, 250);
        itemInventoryPanel.setBackground(Color.blue);
        itemInventoryPanel.setOpaque(true);
        itemInventoryPanel.setVisible(false);
        this.add(itemInventoryPanel);
    }

    private void idCardPanel() {
        idCardPanel.setBackground(Color.gray);
        idCardPanel.setOpaque(true);
        idCardPanel.setBounds(30,100,260,175);
        idCardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        weaponButton();
        idImageLabel();
        if (myCharacterInventory.size() > 0) {
            idRarityLabel(myCharacterInventory.get(0).getRarity());
            idDetailLabel(myCharacterInventory.get(0).getName());
            idImageLabel.setIcon(characterBigPortraits.get(myCharacterInventory.get(0).getName()));
            weaponButton.setIcon(itemPortraits.get(myCharacterInventory.get(0).getItem().getName()));
        }
        idCardPanel.setLayout(null);
        this.add(idCardPanel);
    }

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

    private void idDetailLabel(String name) {
        idDetailLabel.setText(name);
        idDetailLabel.setFont(new Font("Lucida Handwriting",PLAIN,20));
        idDetailLabel.setBounds(120,20,130,30);
        idCardPanel.add(idDetailLabel);
    }

    private void inventoryTitleLabel() {
        JLabel inventoryTitleLabel = new JLabel();
        inventoryTitleLabel.setText("EMPLOYEES");
        inventoryTitleLabel.setForeground(Color.black);
        inventoryTitleLabel.setBounds(90,10,200,20);
        inventoryTitleLabel.setFont(new Font("Lucida Handwriting",PLAIN,20));
        this.add(inventoryTitleLabel);
    }

    private void characterImageLabel() {
        JLabel characterImageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Char_Full_Portrait.png");
        characterImageLabel.setIcon(icon);
        characterImageLabel.setBounds(300,50,150,300);
        this.add(characterImageLabel);
    }

    private void topCharacterButton() {
        topCharacterButton.setBounds(500,50,75,75);
        topCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        topCharacterButton.addActionListener(this);
        this.add(topCharacterButton);
    }

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

    private void midCharacterButton() {
        midCharacterButton.setBounds(500,150,75,75);
        midCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        midCharacterButton.addActionListener(this);
        this.add(midCharacterButton);
    }

    private void botCharacterButton() {
        botCharacterButton.setBounds(500,250,75,75); //75x75
        botCharacterButton.setBorder(BorderFactory.createLineBorder(Color.black));
        botCharacterButton.addActionListener(this);
        this.add(botCharacterButton);
    }

    private void upButton() {
        upButton.setBounds(500,20,75,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button.png");
        upButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        upButton.setBorder(emptyBorder);
        upButton.addActionListener(this);
        this.add(upButton);
    }

    private void downButton() {
        downButton.setBounds(500,340,75,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Scroll_Button 2.png");
        downButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        downButton.setBorder(emptyBorder);
        downButton.addActionListener(this);
        this.add(downButton);
    }

    private void scrollPanel() {
        JPanel scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.gray);
        scrollPanel.setOpaque(true);
        scrollPanel.setBounds(470,0,130,400);
        this.add(scrollPanel);
    }

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
            setId(trackCharacterPortraits(count).get(2));
            System.out.println("BOT CHAR");
        } else if (e.getSource() == midCharacterButton) {
            setId(trackCharacterPortraits(count).get(1));
            System.out.println("MID CHAR");
        } else if (e.getSource() == topCharacterButton) {
            setId(trackCharacterPortraits(count).get(0));
            System.out.println("TOP CHAR");
        } else if (e.getSource() == weaponButton) {
            visible = !visible;
            itemInventoryPanel.setVisible(visible);
            System.out.println("Weapons");
        }
    }

    private void scrollUp() {
        count = count - 3;
        if (myCharacterInventory.size() >= count && count >= 0) {
            addCharacterPortraits(count);
            trackCharacterPortraits(count);
        } else {
            count = count + 3;
        }
    }

    private void scrollDown() {
        count = count + 3;
        if (myCharacterInventory.size() > count) {
            addCharacterPortraits(count);
            trackCharacterPortraits(count);
        } else {
            count = count - 3;
        }
    }

    private void setId(Characters character) {
        if (character.getName() != null) {
            idDetailLabel.setText(character.getName());
            idImageLabel.setIcon(characterBigPortraits.get(character.getName()));
            weaponButton.setIcon(itemPortraits.get(character.getItem().getName()));
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

}
