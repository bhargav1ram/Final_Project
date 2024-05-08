import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DisplayTransactionsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JList<String> transactionsList;
    private JComboBox<String> accountComboBox;  // Declare at class level

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
        // Label for Select Account
        JLabel accountLabel = new JLabel("Select Account:");
        this.add(accountLabel, BorderLayout.NORTH);

        // ComboBox for Selecting Account
        accountComboBox = new JComboBox<>();
        this.add(accountComboBox, BorderLayout.NORTH);

        loadAccountIds();  // Load Account before creating Account object

        // Assuming accountID is selected from accountComboBox
        String accID = (String) accountComboBox.getSelectedItem();
        Account acc = new Account(Session.getInstance().getUserId(), accID, "AccountType"); // Placeholder for account type
        List<String> transactions = acc.getTransactions(); // Fetch transactions

        transactionsList = new JList<>(transactions.toArray(new String[0]));

        // Setting up the scroll pane
        JScrollPane scrollPane = new JScrollPane(transactionsList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        transactionsList.setOpaque(false);
        transactionsList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        transactionsList.setForeground(Color.WHITE);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadAccountIds() {
        String query = "SELECT AccountID FROM bankaccounts WHERE UserID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Session.getInstance().getUserId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String accountId = rs.getString("AccountID");
                accountComboBox.addItem(accountId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading account IDs: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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