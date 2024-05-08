import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class LogoutPanel extends JPanel{

    private BufferedImage backgroundImage;

    public LogoutPanel(){
        // Load the background image
        
        /*try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        this.setLayout(null);
        
        JLabel tile1 = new JLabel("Logged Out Successfully !!!");
        tile1.setBounds(700,200,400,150);
        tile1.setFont(new Font("Verdana",Font.PLAIN,14));
        this.add(tile1);

        JLabel tile2 = new JLabel("Click here to go to Home Screen");
        tile2.setBounds(700,250,400,150);
        tile2.setFont(new Font("Verdana",Font.PLAIN,14));
        this.add(tile2);

        JButton home = new JButton("Here");
        home.setBounds(1000,300,100,40);
        HomeListener hol = new HomeListener();
        home.addActionListener(hol);
        this.add(home);

        
        
        

    }

    /* 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }*/

}