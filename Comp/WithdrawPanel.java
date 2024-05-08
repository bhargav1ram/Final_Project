import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class WithdrawPanel extends JPanel{

    private BufferedImage backgroundImage;

    public WithdrawPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10,10,10);

        JLabel label1 =  new JLabel("Enter Withdraw Amount");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label1,cons);

        JTextField tx1 = new JTextField();
        cons.gridx = 1;
        cons.gridy = 0;
        this.add(tx1,cons);

        // Label for Select Account
        JLabel accountLabel = new JLabel("Select Currency:");
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(accountLabel, cons);

        // ComboBox for Selecting Account
        JComboBox<String> accountComboBox = new JComboBox<>(new String[]{"INR", "USD", "EUR"});
        cons.gridx = 1;
        cons.gridy = 1;
        this.add(accountComboBox, cons);

        JButton opUsrAcc = new JButton("Withdraw");
        cons.gridx = 0;
        cons.gridy = 2;
        this.add(opUsrAcc,cons);
        


    }

}