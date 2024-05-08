import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class StockPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTable stocksTable;

    public StockPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        String[] columnNames = {"Stock Name", "Symbol", "Current Price", "Profit/Loss", "Shares", "Buy", "Sell"};
        Object[][] data = {
            {"Apple Inc.", "AAPL", 150.00, "+5.00", "", "Buy", "Sell"},
            {"Microsoft Corp.", "MSFT", 250.00, "+10.00", "", "Buy", "Sell"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make shares editable, rest non-editable
                return column == 4;
            }
        };

        stocksTable = new JTable(model);
        stocksTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        stocksTable.setFillsViewportHeight(true);

        // Set custom renderers and editors for Buy and Sell buttons
        stocksTable.getColumn("Buy").setCellRenderer(new ButtonRenderer());
        stocksTable.getColumn("Buy").setCellEditor(new ButtonEditor(new JCheckBox(), "Buy"));

        stocksTable.getColumn("Sell").setCellRenderer(new ButtonRenderer());
        stocksTable.getColumn("Sell").setCellEditor(new ButtonEditor(new JCheckBox(), "Sell"));

        JScrollPane scrollPane = new JScrollPane(stocksTable);
        add(scrollPane, BorderLayout.CENTER);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Custom renderer for buttons
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom editor for buttons
    class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox, String label) {
            super(checkBox);
            this.label = label;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                // Placeholder action
                System.out.println(label + " button clicked for row " + row);
                // Actual actions like buying/selling should go here
            });
            return button;
        }
    }
}