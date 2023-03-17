package ui;

import javax.swing.*;
import java.awt.*;


public class MainMenuGUI extends JFrame {



    public static void main(String[] args) {
        JFrame f = new JFrame("Gacha Game");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // clicking the 'x' button exits
        f.setLayout(new FlowLayout());
        f.setResizable(false); // prevents from changing dimensions of frame
        f.setSize(1080,720); // sets size of frame
        f.getContentPane().setBackground(Color.white); //changes frame bg color
        f.add(new JLabel("Hello, world!"));
        f.add(new JButton("Gacha"));
        f.setVisible(true); // makes frame visible
        //SwingUtilities.invokeLater(new MainMenuGUI());
    }

    //public MainMenuGUI() {
        //f = new JFrame("Gacha Game");
        //f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //f.setLayout(new FlowLayout());
        //f.setSize(1000,1000);
        //f.add(new JLabel("Hello, world!"));
        //f.add(new JButton("Gacha"));
    //}

    //@Override
    //public void run() {
        // Arrange the components inside the window
        //f.pack();
        // By default, the window is not visible. Make it visible.
        //f.setVisible(true);
    //}
}
