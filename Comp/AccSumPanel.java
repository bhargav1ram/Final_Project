import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;

public class AccSumPanel extends JPanel{

    private BufferedImage backgroundImage;

    public AccSumPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String[] columnNames = {"Account Number", "Account Type", "Balance"};
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

}