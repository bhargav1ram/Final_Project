import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;

public class AdminTradePanel extends JPanel {
    private BufferedImage backgroundImage;
  

    public AdminTradePanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton updateStocksButton = new JButton("Update Stocks");
        JButton addNewStocksButton = new JButton("Add New Stocks");
        JButton setExchangeRateButton = new JButton("Set Exchange Rate");

        updateStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println( "update stocks Clicked" );
                AdminDefPanel.shiftPanel(new AdminUpStocksPanel());
            }
        });

        addNewStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println( "Add New Stocks Clicked" );
                AdminDefPanel.shiftPanel(new AdminNewStocksPanel());
            }
        });

        setExchangeRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println( "Set Exchange Rate Clicked" );
                AdminDefPanel.shiftPanel(new AdminXchgRatePanel());
            }
        });

        this.add(updateStocksButton);
        this.add(addNewStocksButton);
        this.add(setExchangeRateButton);

        

        
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}