import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccSumPanel extends JPanel {
    private BufferedImage backgroundImage;

    public AccSumPanel() {
        // Load the background image
        try {
            //backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        String[] columnNames = {"Account Number", "Account Type", "USD Balance","INR Balance","EUR Balance"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable accountTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);

        // Styling
        accountTable.setOpaque(true);
        accountTable.setShowGrid(false);
        accountTable.setFont(new Font("SansSerif", Font.BOLD, 12));
        accountTable.setForeground(Color.WHITE);
        accountTable.setSelectionBackground(new Color(255, 255, 255, 100));

        loadAccountData(tableModel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadAccountData(DefaultTableModel tableModel) {
        String query = "SELECT AccountID, AccountType, USDBalance, INRBalance, EURBalance FROM bankaccounts WHERE UserID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, Session.getInstance().getUserId()); // Assuming Session class stores user info
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String accountID = rs.getString("AccountID");
                String accountType = rs.getString("AccountType");
                String balance1 = rs.getString("USDBalance"); // Format as needed
                String balance2 = rs.getString("INRBalance"); // Format as needed
                String balance3 = rs.getString("EURBalance"); // Format as needed
                tableModel.addRow(new Object[]{accountID, accountType, balance1,balance2,balance3});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading account data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}