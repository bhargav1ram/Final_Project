import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class TradePanel extends JPanel{

    private BufferedImage backgroundImage;

    public TradePanel(){
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

        JButton stock = new JButton("Stocks");
        
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(stock,cons);
        StockListener sl = new StockListener();
        stock.addActionListener(sl);

        JButton mov =  new JButton("Funds Movement");
        
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(mov,cons);
        MoveFundListener mfl = new MoveFundListener();
        mov.addActionListener(mfl);

    
    }

}