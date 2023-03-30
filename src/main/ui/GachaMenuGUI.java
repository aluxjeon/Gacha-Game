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

public class GachaMenuGUI extends JFrame implements ActionListener {
    private JButton characterBannerButton;
    private JButton itemBannerButton;
    private JButton currencyButton;
    private JButton showPityButton;
    private JButton tenPullButton;
    private JButton onePullButton;
    private JButton backButton;
    private final JButton currencyIncreaseButton = new JButton();
    private final JButton closeButton = new JButton();

    private final JPanel pullPanel = new JPanel();
    private JPanel currencyPanel;
    private final JLabel characterBannerLabel = new JLabel();
    private final JLabel itemBannerLabel = new JLabel();
    private final JLabel pullLabel = new JLabel();

    private final Map<String,ImageIcon> characterBigPortraits = new HashMap<String,ImageIcon>();
    private final Map<String,ImageIcon> itemPortraits = new HashMap<String,ImageIcon>();
    private final ImageIcon poopIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/POOP.png");
    private final ArrayList<Characters> tenPullCharacterList = new ArrayList<>();
    private final ArrayList<Item> tenPullItemList = new ArrayList<>();
    private Boolean visibility = false;

    private final Currency myCurrency;
    private final CharacterGacha characterGacha;
    private final ItemGacha itemGacha;

    public GachaMenuGUI(Currency myCurrency, CharacterGacha characterGacha, ItemGacha itemGacha) {
        this.myCurrency = myCurrency;
        this.characterGacha = characterGacha;
        this.itemGacha = itemGacha;
        initializePortraits();
        makeGachaMenuGUI();
    }

    //MODIFIES: this
    //EFFECTS: Gets all the corresponding image-icons from desktop folder & adds it to
    // either the character portraits or item portraits
    private void initializePortraits() {
        for (Characters c : this.characterGacha.getFiveStarCharacterRoster()) {
            ImageIcon bigIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + "ID.png");
            characterBigPortraits.put(c.getName(),bigIcon);
        }

        for (Characters c : this.characterGacha.getFourStarCharacterRoster()) {
            ImageIcon bigIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + c.getName() + "ID.png");
            characterBigPortraits.put(c.getName(),bigIcon);
        }

        for (Item i : this.itemGacha.getFiveStarItemRoster()) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + "M.png");
            itemPortraits.put(i.getName(),icon);
        }
        for (Item i : this.itemGacha.getFourStarItemRoster()) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + "M.png");
            itemPortraits.put(i.getName(),icon);
        }
    }

    //MODIFIES: this
    //EFFECTS: Makes Gacha Menu GUI and initialize Gacha Menu GUI's GUI components
    public void makeGachaMenuGUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setSize(600,400);
        this.getContentPane().setBackground(Color.white);
        JLabel menuTitleLabel = new JLabel();
        menuTitleLabel.setText("Hire");
        menuTitleLabel.setFont(new Font("Lucida Handwriting",PLAIN,15));
        menuTitleLabel.setBounds(85,10,50,20);
        menuTitleLabel.setForeground(Color.black);
        this.add(menuTitleLabel);
        initializeGuiComp();
        this.setLayout(null);
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: Helper method that initializes all the GUI components
    private void initializeGuiComp() {
        createPullPanel();
        createBackButton();
        singlePullButton();
        createTenPullButton();
        createPityButton();
        createCurrencyComponents();
        createCharacterBannerLabel();
        createItemBannerLabel();
        createCharacterBannerButton();
        createItemBannerButton();
        createPillarPanel();
    }


    //==============================Pull GUI==========================
    //MODIFIES: this
    //EFFECTS: Initializes onePullButton & adds to GUI
    private void singlePullButton() {
        onePullButton = new JButton();
        onePullButton.setBounds(30,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button.png");
        onePullButton.setIcon(icon);
        onePullButton.addActionListener(this);
        this.add(onePullButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes tenPullButton & adds to GUI
    private void createTenPullButton() {
        tenPullButton = new JButton();
        tenPullButton.setBounds(130,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button 3.png");
        tenPullButton.setIcon(icon);
        tenPullButton.addActionListener(this);
        this.add(tenPullButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes pullPanel & adds to GUI
    private void createPullPanel() {
        closeButton.setBounds(0,0,20,20);
        closeButton.addActionListener(this);
        pullLabel.setBounds(250,0,100,100);
        pullPanel.setLayout(null);
        pullPanel.setBounds(0,125,600,100);
        pullPanel.setBackground(Color.lightGray);
        pullPanel.setVisible(false);
        pullPanel.add(pullLabel);
        pullPanel.add(closeButton);
        this.add(pullPanel);
    }

    //========================Other GUI========================
    //MODIFIES: this
    //EFFECTS: Initializes showPityButton & adds to GUI
    private void createPityButton() {
        showPityButton = new JButton();
        showPityButton.setBounds(330,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button 2.png");
        showPityButton.setIcon(icon);
        showPityButton.addActionListener(this);
        this.add(showPityButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes backButton & adds to GUI
    private void createBackButton() {
        backButton = new JButton();
        backButton.setBounds(30,10,50,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Back_Button.png");
        backButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backButton.setBorder(emptyBorder);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes currencyButton, currencyPanel, and currencyIncreaseButton.
    // currencyButton &  currencyPanel is added to GUI, but currencyIncreaseButton
    // is added to currencyPanel
    private void createCurrencyComponents() {
        currencyButton = new JButton();
        currencyButton.setBounds(380,10,50,20);
        currencyButton.setBackground(Color.black);
        currencyButton.setOpaque(true);
        currencyButton.addActionListener(this);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        currencyButton.setFont(new Font("Lucida Handwriting",PLAIN,15));
        this.add(currencyButton);

        currencyPanel = new JPanel();
        currencyPanel.setBounds(380,30,50,25);
        currencyPanel.setBackground(Color.lightGray);
        currencyPanel.setOpaque(true);
        currencyPanel.setVisible(false);
        currencyPanel.setLayout(null);
        this.add(currencyPanel);

        currencyIncreaseButton.setBounds(12,0,26,25);
        currencyIncreaseButton.setText("+");
        currencyIncreaseButton.setBackground(Color.yellow);
        currencyIncreaseButton.setOpaque(true);
        currencyIncreaseButton.addActionListener(this);
        currencyPanel.add(currencyIncreaseButton);
    }

    //=======================Right Side Pillar==================
    //MODIFIES: this
    //EFFECTS: Initializes pillarPanel and adds to GUI
    private void createPillarPanel() {
        JPanel pillarPanel = new JPanel();
        pillarPanel.setBounds(490,0,50,400);
        pillarPanel.setBackground(Color.lightGray);
        this.add(pillarPanel);
    }

    //MODIFIES: this
    //EFFECTS: Initializes characterBannerButton and adds to GUI
    private void createCharacterBannerButton() {
        characterBannerButton = new JButton();
        characterBannerButton.setBounds(465,100,100,75);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/CharacterBannerButton.png");
        characterBannerButton.setIcon(icon);
        characterBannerButton.setBorder(BorderFactory.createLineBorder(Color.black));
        characterBannerButton.addActionListener(this);
        this.add(characterBannerButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes itemBannerButton and adds to GUI
    private void createItemBannerButton() {
        itemBannerButton = new JButton();
        itemBannerButton.setBounds(465,200,100,75);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/ItemBannerButton.png");
        itemBannerButton.setIcon(icon);
        itemBannerButton.setBorder(BorderFactory.createLineBorder(Color.black));
        itemBannerButton.addActionListener(this);
        this.add(itemBannerButton);
    }

    //MODIFIES: this
    //EFFECTS: Initializes characterBannerLabel and adds to GUI
    private void createCharacterBannerLabel() {
        characterBannerLabel.setBounds(30,30,400,275);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/CharBanner.png");
        characterBannerLabel.setIcon(icon);
        characterBannerLabel.setVisible(true);
        characterBannerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(characterBannerLabel);
    }

    //MODIFIES: this
    //EFFECTS: Initializes itemBannerLabel and adds to GUI
    private void createItemBannerLabel() {
        itemBannerLabel.setBounds(30,30,400,275);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/ItemBanner.png");
        itemBannerLabel.setIcon(icon);
        itemBannerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        itemBannerLabel.setVisible(false);
        this.add(itemBannerLabel);
    }

    //================================ACTION EVENTS==============================
    //MODIFIES: this
    //EFFECTS: Performs correct corresponding actions based on which button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == characterBannerButton) {
            characterBannerLabel.setVisible(true);
            itemBannerLabel.setVisible(false);
        } else if (e.getSource() == itemBannerButton) {
            characterBannerLabel.setVisible(false);
            itemBannerLabel.setVisible(true);
        } else if (e.getSource() == currencyButton) {
            visibility = !visibility;
            currencyPanel.setVisible(visibility);
        } else if (e.getSource() == currencyIncreaseButton) {
            this.myCurrency.addCurrency(100);
            currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        } else if (e.getSource() == backButton) {
            this.dispose();
            MainMenuGUI mainMenuGUI = new MainMenuGUI(myCurrency,characterGacha,itemGacha);
            System.out.println("Back");
        } else {
            gachaRelatedButtonActions(e);
        }
    }

    //MODIFIES: this
    //EFFECTS: Performs correct corresponding actions based on which gacha buttons are pressed
    private void gachaRelatedButtonActions(ActionEvent e) {
        if (e.getSource() == onePullButton && characterBannerLabel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            pullCharacter();
        } else if (e.getSource() == onePullButton && itemBannerLabel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            pullItem();
        } else if (e.getSource() == tenPullButton && characterBannerLabel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            tenPullCharacter();
        } else if (e.getSource() == tenPullButton && itemBannerLabel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            tenPullItem();
        } else if (e.getSource() == showPityButton && characterBannerLabel.isVisible()) {
            System.out.println("Character Pity: " + characterGacha.getPity());
        } else if (e.getSource() == showPityButton && itemBannerLabel.isVisible()) {
            System.out.println("Item Pity: " + itemGacha.getPity());
        } else if (e.getSource() == closeButton && characterBannerLabel.isVisible()) {
            displayTenPulledCharacter();
        } else if (e.getSource() == closeButton && itemBannerLabel.isVisible()) {
            displayTenPulledItem();
        }
    }

    //==========================HELPER METHODS FOR ACTION EVENTS=================
    //MODIFIES: this
    //EFFECTS: Pull character & displays the character by calling displayPulledCharacter()
    private void pullCharacter() {
        int prev = characterGacha.getCharacterList().size();
        characterGacha.pull();
        myCurrency.subCurrency(100);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        displayPulledCharacter(prev);
    }

    //MODIFIES: this
    //EFFECTS: Pull item & displays the item by calling displayPulledItem()
    private void pullItem() {
        int prev = itemGacha.getItemList().size();
        itemGacha.pull();
        myCurrency.subCurrency(100);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        displayPulledItem(prev);
    }

    //MODIFIES: this
    //EFFECTS: Does a ten pull for characters and only display new characters. ELSE display poop
    private void tenPullCharacter() {
        int listSize = characterGacha.getCharacterList().size();

        characterGacha.tenPull();
        myCurrency.subCurrency(1000);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));

        if (characterGacha.getCharacterList().size() > listSize) {
            for (int i = listSize;i < characterGacha.getCharacterList().size();i++) {
                tenPullCharacterList.add(characterGacha.getCharacterList().get(i));
            }
            pullLabel.setIcon(characterBigPortraits.get(tenPullCharacterList.get(0).getName()));
            tenPullCharacterList.remove(0);
            pullPanel.setVisible(true);
        } else {
            pullLabel.setIcon(poopIcon);
            pullPanel.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: Does a ten pull for items and only display new items. ELSE display poop
    private void tenPullItem() {
        int listSize = itemGacha.getItemList().size();

        itemGacha.tenPull();
        myCurrency.subCurrency(1000);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));

        if (itemGacha.getItemList().size() > listSize) {
            for (int i = listSize;i < itemGacha.getItemList().size();i++) {
                tenPullItemList.add(itemGacha.getItemList().get(i));
            }
            pullLabel.setIcon(itemPortraits.get(tenPullItemList.get(0).getName()));
            tenPullItemList.remove(0);
            pullPanel.setVisible(true);
        } else {
            pullLabel.setIcon(poopIcon);
            pullPanel.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: If there are new characters pulled, then display their icon and remove then from
    // the tenPullCharacterList. ELSE just set pullPanel to not visible
    private void displayTenPulledCharacter() {
        if (tenPullCharacterList.size() > 0) {
            pullLabel.setIcon(characterBigPortraits.get(tenPullCharacterList.get(0).getName()));
            tenPullCharacterList.remove(0);
        } else {
            pullPanel.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: If there are new items pulled, then display their icon and remove then from
    // the tenPullItemList. ELSE just set pullPanel to not visible
    private void displayTenPulledItem() {
        if (tenPullItemList.size() > 0) {
            pullLabel.setIcon(itemPortraits.get(tenPullItemList.get(0).getName()));
            tenPullItemList.remove(0);
        } else {
            pullPanel.setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: if the item inventory increased, then get the last item from item
    // inventory and display its icon on pullLabel.
    // ELSE, display poop. Set pullLabel to visible
    private void displayPulledItem(Integer prev) {
        int prevListSize = prev;
        if (itemGacha.getItemList().size() > prevListSize) {
            String name = itemGacha.getItemList().get(
                    itemGacha.getItemList().size() - 1).getName();
            pullLabel.setIcon(itemPortraits.get(name));
        } else {
            pullLabel.setIcon(poopIcon);
        }
        pullPanel.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: if the character inventory increased, then get the last character from
    // character inventory and display its icon on pullLabel.
    // ELSE, display poop. Set pullLabel to visible
    private void displayPulledCharacter(Integer prev) {
        int prevListSize = prev;
        if (characterGacha.getCharacterList().size() > prevListSize) {
            String name = characterGacha.getCharacterList().get(
                    characterGacha.getCharacterList().size() - 1).getName();
            pullLabel.setIcon(characterBigPortraits.get(name));
        } else {
            pullLabel.setIcon(poopIcon);
        }
        pullPanel.setVisible(true);
    }
}
