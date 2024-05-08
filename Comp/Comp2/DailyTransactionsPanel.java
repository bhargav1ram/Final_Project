import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;

public class DailyTransactionsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTable transactionsTable;
    private DefaultTableModel model;

    public DailyTransactionsPanel() {
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
        Object[][] data = {};  // Placeholder for initial data

        // Set up the table model and table
        model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        transactionsTable = new JTable(model);
        transactionsTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add a listener to the scroll pane to load more data dynamically
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            private int lastMaximum = Integer.MIN_VALUE;

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int currentMaximum = scrollPane.getVerticalScrollBar().getMaximum();
                if (currentMaximum != lastMaximum) {
                    lastMaximum = currentMaximum;
                    if (scrollPane.getVerticalScrollBar().getValue() >= currentMaximum - scrollPane.getHeight() - 100) {
                        loadMoreData();  // Method to load more data
                    }
                }
            }
        });
    }

    private void loadMoreData() {
        // This method should fetch additional data and update the table model.
        System.out.println("Loading more data...");
        // Example to add more data:
        model.addRow(new Object[]{"New ID", "New Amount", "New Date"});
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}