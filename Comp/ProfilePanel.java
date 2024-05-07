import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ProfilePanel extends JPanel{

    private BufferedImage backgroundImage;

    public ProfilePanel(){
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

        JLabel nameLabel =  new JLabel("Name");
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(nameLabel,cons);

        JLabel name =  new JLabel("Name");
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(name,cons);

        
;

        

    }

}