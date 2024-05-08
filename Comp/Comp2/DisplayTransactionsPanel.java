import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.table.*;

public class DisplayTransactionsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTable transactionsTable;

    public DisplayTransactionsPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        String[] columnNames = {"Transaction ID", "Amount", "Date"};
        Object[][] data = {};  // Example data, should be dynamically loaded

        // Setup the table model and the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        transactionsTable = new JTable(model);
        transactionsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add navigation buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Add listeners to buttons
        prevButton.addActionListener(e -> navigateTransactions(-1));
        nextButton.addActionListener(e -> navigateTransactions(1));

        // Add button panel to the south region of this panel
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void navigateTransactions(int direction) {
        // Implement the functionality to fetch previous or next 20 transactions
        // direction > 0 for next, direction < 0 for previous
        // This might involve changing the model data of the table and refreshing it
        System.out.println("Navigate: " + (direction > 0 ? "Next" : "Previous"));
        // Example: loadNextTransactions(); or loadPreviousTransactions();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}