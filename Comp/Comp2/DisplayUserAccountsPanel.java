import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.table.*;



public class DisplayUserAccountsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTable usersTable;

    public DisplayUserAccountsPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        
        String[] columnNames = {"User ID", "Name", "Role", "Last Login"};
        Object[][] data = {
            {"1", "Alice", "Admin", "2021-01-01"},
            {"2", "Bob", "User", "2021-01-02"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };

        usersTable = new JTable(model);
        usersTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        usersTable.setFillsViewportHeight(true);
        usersTable.getColumn("User ID").setCellRenderer(new ButtonRenderer());

        usersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = usersTable.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / usersTable.getRowHeight();

                if (row < usersTable.getRowCount() && row >= 0 && column < usersTable.getColumnCount() && column >= 0) {
                    Object value = usersTable.getValueAt(row, column);
                    if ("User ID".equals(usersTable.getColumnName(column))) {
                        // This is where you can handle the click event for User ID
                        System.out.println("User ID " + value + " clicked.");
                        AdminDefPanel.shiftPanel(new DisplayUsrPanel());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(usersTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    class ButtonRenderer extends JLabel implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setForeground(Color.BLUE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}