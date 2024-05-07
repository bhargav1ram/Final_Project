import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CreAccPanel extends JPanel{

    private BufferedImage backgroundImage;

    public CreAccPanel(){
        this.setLayout(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel menu = new JPanel(new GridBagLayout());
        //Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        //Border line = BorderFactory.createLineBorder(Color.BLACK);
        
        menu.setSize(200,900);
        menu.setLocation(50, 100);
        //menu.setBorder(BorderFactory.createCompoundBorder(line,pad));
        this.add(menu);

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        //cons.insets = new Insets(10, 10,10,10);
        //cons.weightx = 1.0;
        //cons.weighty = 1.0;

        MJButton prof = new MJButton("Profile");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(prof,cons);
       
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }
}