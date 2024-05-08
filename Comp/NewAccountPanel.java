import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class NewAccountPanel extends JPanel {
    private BufferedImage backgroundImage;

    public NewAccountPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);
        
        // Account type label
        JLabel label1 = new JLabel("Select Account Type");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label1, cons);
        
        // Radio buttons for account type
        JRadioButton currentAccount = new JRadioButton("Current");
        JRadioButton savingsAccount = new JRadioButton("Savings");
        
        // Button group to ensure only one can be selected
        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(currentAccount);
        accountTypeGroup.add(savingsAccount);
        
        // Adding radio buttons to the panel
        cons.gridx = 1;
        this.add(currentAccount, cons);
        cons.gridy = 1;
        this.add(savingsAccount, cons);

        // Label for amount
        JLabel label2 = new JLabel("Enter Amount");
        cons.gridy = 2;
        cons.gridx = 0;
        this.add(label2, cons);

        // Text field for amount
        JTextField tx2 = new JTextField(10);
        cons.gridx = 1;
        this.add(tx2, cons);

        // Submit button
        JButton submit = new JButton("Submit");
        cons.gridy = 3;
        cons.gridx = 0;
        cons.gridwidth = 2;
        this.add(submit, cons);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}