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

        this.setLayout(new BorderLayout());
        
        String[] columnNames = {"Loan ID", "Amount", "Date Taken", "Interest Rate", "Status"};
        Object[][] data = {}; // Initialize with empty data; will be populated dynamically

        JTable loansTable = new JTable(data, columnNames);
        loansTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        loansTable.setFillsViewportHeight(true);

        // Put the table into a JScrollPane to handle scrolling
        JScrollPane scrollPane = new JScrollPane(loansTable);
        add(scrollPane, BorderLayout.CENTER);


    }

}