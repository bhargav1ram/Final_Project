import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class DisplayPanel extends JPanel{

    private BufferedImage backgroundImage;

    public DisplayPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());

        String[] accounts = {"Account 1", "Account 2", "Account 3"};
        JComboBox<String> accountComboBox = new JComboBox<>(accounts);
        add(accountComboBox, BorderLayout.NORTH); // Add ComboBox at the top
        
        String[] columnNames = {"Transaction ID", "Amount", "Date Taken"};
        Object[][] data = {}; // Initialize with empty data; will be populated dynamically

        JTable transTable = new JTable(data, columnNames);
        transTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        transTable.setFillsViewportHeight(true);

        // Put the table into a JScrollPane to handle scrolling
        JScrollPane scrollPane = new JScrollPane(transTable);
        add(scrollPane, BorderLayout.CENTER);


    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}