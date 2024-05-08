import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;

public class AdminLoanPanel extends JPanel {
    private BufferedImage backgroundImage;
  

    public AdminLoanPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        String[] columnNames = {"Loan ID", "Borrower Name", "Amount", "Interest Rate", "Start Date", "Due Date", "Status"};
        Object[][] data = {
            {"L001", "Alice Smith", "$5,000", "5%", "2021-01-01", "2023-01-01", "Active"},
            {"L002", "Bob Johnson", "$3,000", "4.5%", "2021-06-15", "2023-06-15", "Pending"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        JTable loansTable = new JTable(model);
        loansTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(loansTable);
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