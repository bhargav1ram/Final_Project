import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class MoveFundPanel extends JPanel{

    private BufferedImage backgroundImage;

    public MoveFundPanel(){
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

        JLabel label1 =  new JLabel("Total UnRealized Profit ");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label1,cons);


        JLabel label2 =  new JLabel("100");
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label2,cons);


        JLabel label3 =  new JLabel("Total Realized Profit");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label3,cons);

        JLabel label4 =  new JLabel("100");
        cons.gridx = 1;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label4,cons);

       


    }

}