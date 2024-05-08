import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class CurrLoanPanel extends JPanel{

    private BufferedImage backgroundImage;

    public CurrLoanPanel(){
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

        JLabel label1 =  new JLabel("CuurLoan ");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label1,cons);

        JButton opUsrAcc = new JButton("My Accounts");
        cons.gridx = 1;
        cons.gridy = 0;
        this.add(opUsrAcc,cons);
        
        SelAccListener sal = new SelAccListener();
        opUsrAcc.addActionListener(sal);

        JLabel label2 =  new JLabel("Open a New Account ?");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label2,cons);

        JButton opNewAcc = new JButton("New Account");
        cons.gridx = 1;
        cons.gridy = 1;
        this.add(opNewAcc,cons);
        
        NewAccListener nal = new NewAccListener();
        opNewAcc.addActionListener(nal);


    }

}