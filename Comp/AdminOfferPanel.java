import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;

public class AdminOfferPanel extends JPanel {
    private BufferedImage backgroundImage;
  

    public AdminOfferPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] columnNames = {"Offers", "Account Type", "Type"};
        // Dummy data - Replace this with actual data retrieval from database
        Object[][] data = {
            {"123456", "Checking", "$3,500"},
            {"654321", "Savings", "$5,200"}
        };

        
        
        

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable accountTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Styling
        accountTable.setOpaque(false);
        accountTable.setShowGrid(false);
        accountTable.setFont(new Font("SansSerif", Font.BOLD, 12));
        accountTable.setForeground(Color.WHITE);
        accountTable.setSelectionBackground(new Color(255, 255, 255, 100));
        this.add(scrollPane, BorderLayout.CENTER);

        
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}