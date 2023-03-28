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
    private JPanel currencyPanel;
    private Boolean visibility = false;
    private final JButton currencyIncreaseButton = new JButton();
    private final JPanel characterBannerPanel = new JPanel();
    private final JPanel itemBannerPanel = new JPanel();
    private final JPanel pullPanel = new JPanel();
    private final JButton closeButton = new JButton();
    private final JLabel pullLabel = new JLabel();
    private final Map<String,ImageIcon> characterBigPortraits = new HashMap<String,ImageIcon>();
    private final Map<String,ImageIcon> itemPortraits = new HashMap<String,ImageIcon>();
    private final ImageIcon poopIcon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/POOP.png");
    private final ArrayList<Characters> tenPullCharacterList = new ArrayList<>();
    private final ArrayList<Item> tenPullItemList = new ArrayList<>();

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
                    + i.getName() + ".png");
            itemPortraits.put(i.getName(),icon);
        }
        for (Item i : this.itemGacha.getFourStarItemRoster()) {
            ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/"
                    + i.getName() + ".png");
            itemPortraits.put(i.getName(),icon);
        }
    }

    //MODIFIES: this
    //EFFECTS:
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

    private void initializeGuiComp() {
        pullPanel();
        backButton();
        singlePullButton();
        tenPullButton();
        pityButton();
        currencyButton();
        characterBannerPanel();
        itemBannerPanel();
        characterBannerButton();
        itemBannerButton();
        pillarPanel();
    }


    //==============================Pull GUI==========================
    //MODIFIES: this
    private void singlePullButton() {
        onePullButton = new JButton();
        onePullButton.setBounds(30,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button.png");
        onePullButton.setIcon(icon);
        onePullButton.addActionListener(this);
        this.add(onePullButton);
    }

    private void tenPullButton() {
        tenPullButton = new JButton();
        tenPullButton.setBounds(130,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button 3.png");
        tenPullButton.setIcon(icon);
        tenPullButton.addActionListener(this);
        this.add(tenPullButton);
    }

    private void pullPanel() {
        closeButton.setBounds(0,0,20,20);
        closeButton.addActionListener(this);
        pullLabel.setBounds(250,0,100,100);
        pullPanel.setLayout(null);
        pullPanel.setBounds(0,125,600,100);
        pullPanel.setBackground(Color.gray);
        pullPanel.setVisible(false);
        pullPanel.add(pullLabel);
        pullPanel.add(closeButton);
        this.add(pullPanel);
    }

    //========================Other GUI========================
    private void pityButton() {
        showPityButton = new JButton();
        showPityButton.setBounds(330,305,100,50);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Button 2.png");
        showPityButton.setIcon(icon);
        showPityButton.addActionListener(this);
        this.add(showPityButton);
    }

    //MODIFIES: this
    //EFFECTS: makes the back arrow button with an image-icon of a left-pointing arrow
    private void backButton() {
        backButton = new JButton();
        backButton.setBounds(30,10,50,20);
        ImageIcon icon = new ImageIcon("/Users/aluxj702/Desktop/CPSC210IMAGES folder/Back_Button.png");
        backButton.setIcon(icon);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        backButton.setBorder(emptyBorder);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    private void currencyButton() {
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
        currencyPanel.setBackground(Color.black);
        currencyPanel.setOpaque(true);
        currencyPanel.setVisible(false);
        currencyPanel.setLayout(null);
        this.add(currencyPanel);

        currencyIncreaseButton.setBounds(12,0,26,25);
        currencyIncreaseButton.setBackground(Color.yellow);
        currencyIncreaseButton.setOpaque(true);
        currencyIncreaseButton.addActionListener(this);
        currencyPanel.add(currencyIncreaseButton);
    }

    //=======================Right Side Pillar==================
    private void pillarPanel() {
        JPanel pillarPanel = new JPanel();
        pillarPanel.setBounds(490,0,50,400);
        pillarPanel.setBackground(Color.gray);
        this.add(pillarPanel);
    }

    private void characterBannerButton() {
        characterBannerButton = new JButton();
        characterBannerButton.setBounds(465,100,100,75);
        characterBannerButton.setBackground(Color.yellow);
        characterBannerButton.setOpaque(true);
        characterBannerButton.addActionListener(this);
        this.add(characterBannerButton);
    }

    private void itemBannerButton() {
        itemBannerButton = new JButton();
        itemBannerButton.setBounds(465,200,100,75);
        itemBannerButton.setBackground(Color.yellow);
        itemBannerButton.setOpaque(true);
        itemBannerButton.addActionListener(this);
        this.add(itemBannerButton);
    }

    private void characterBannerPanel() {
        characterBannerPanel.setBounds(30,30,400,275);
        characterBannerPanel.setBackground(Color.blue);
        characterBannerPanel.setVisible(true);
        this.add(characterBannerPanel);
    }

    private void itemBannerPanel() {
        itemBannerPanel.setBounds(30,30,400,275);
        itemBannerPanel.setBackground(Color.red);
        itemBannerPanel.setVisible(false);
        this.add(itemBannerPanel);
    }

    //================================ACTION EVENTS==============================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == characterBannerButton) {
            characterBannerPanel.setVisible(true);
            itemBannerPanel.setVisible(false);
        } else if (e.getSource() == itemBannerButton) {
            characterBannerPanel.setVisible(false);
            itemBannerPanel.setVisible(true);
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
            gachaFeedback(e);
        }
    }

    private void gachaFeedback(ActionEvent e) {
        if (e.getSource() == onePullButton && characterBannerPanel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            pullCharacter();
        } else if (e.getSource() == onePullButton && itemBannerPanel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 100))) {
            pullItem();
        } else if (e.getSource() == tenPullButton && characterBannerPanel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            tenPullCharacter();
        } else if (e.getSource() == tenPullButton && itemBannerPanel.isVisible()
                && (0 <= ((this.myCurrency.getCurrency()) - 1000))) {
            tenPullItem();
        } else if (e.getSource() == showPityButton && characterBannerPanel.isVisible()) {
            System.out.println("Character Pity: " + characterGacha.getPity());
        } else if (e.getSource() == showPityButton && itemBannerPanel.isVisible()) {
            System.out.println("Item Pity: " + itemGacha.getPity());
        } else if (e.getSource() == closeButton && characterBannerPanel.isVisible()) {
            displayTenPulledCharacter();
        } else if (e.getSource() == closeButton && itemBannerPanel.isVisible()) {
            displayTenPulledItem();
        }
    }

    //==========================HELPER METHODS FOR ACTION EVENTS=================
    private void pullCharacter() {
        characterGacha.pull();
        myCurrency.subCurrency(100);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        displayPulledCharacter();
    }

    private void pullItem() {
        itemGacha.pull();
        myCurrency.subCurrency(100);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));
        displayPulledItem();
    }

    private void tenPullCharacter() {
        int listSize = characterGacha.getCharacterList().size();

        characterGacha.tenPull();
        myCurrency.subCurrency(1000);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));

        if (characterGacha.getCharacterList().size() > 0) {
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

    private void tenPullItem() {
        int listSize = itemGacha.getItemList().size();

        itemGacha.tenPull();
        myCurrency.subCurrency(1000);
        currencyButton.setText(String.valueOf(this.myCurrency.getCurrency()));

        if (itemGacha.getItemList().size() > 0) {
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

    private void displayTenPulledCharacter() {
        if (tenPullCharacterList.size() > 0) {
            pullLabel.setIcon(characterBigPortraits.get(tenPullCharacterList.get(0).getName()));
            tenPullCharacterList.remove(0);
        } else {
            pullPanel.setVisible(false);
        }
    }

    private void displayTenPulledItem() {
        if (tenPullItemList.size() > 0) {
            pullLabel.setIcon(itemPortraits.get(tenPullItemList.get(0).getName()));
            tenPullItemList.remove(0);
        } else {
            pullPanel.setVisible(false);
        }
    }

    private void displayPulledItem() {
        if (itemGacha.getItemList().size() > 0) {
            String name = itemGacha.getItemList().get(
                    itemGacha.getItemList().size() - 1).getName();
            pullLabel.setIcon(itemPortraits.get(name));
            pullPanel.setVisible(true);
        } else {
            pullLabel.setIcon(poopIcon);
            pullPanel.setVisible(true);
        }
    }

    private void displayPulledCharacter() {
        if (characterGacha.getCharacterList().size() > 0) {
            String name = characterGacha.getCharacterList().get(
                    characterGacha.getCharacterList().size() - 1).getName();
            pullLabel.setIcon(characterBigPortraits.get(name));
            pullPanel.setVisible(true);
        } else {
            pullLabel.setIcon(poopIcon);
            pullPanel.setVisible(true);
        }
    }
}
