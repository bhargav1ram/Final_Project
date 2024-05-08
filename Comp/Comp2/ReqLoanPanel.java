import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReqLoanPanel extends JPanel{

    private BufferedImage backgroundImage;
    JComboBox<String> accountComboBox;

    public ReqLoanPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10,10,10);

        JLabel loanAmountLabel = new JLabel("Enter Loan Amount:");
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(loanAmountLabel, cons);

        // TextField for Loan Amount
        JTextField loanAmountField = new JTextField(15);
        cons.gridx = 1;
        this.add(loanAmountField, cons);

        // Label for Select Account
        JLabel accountLabel = new JLabel("Select Account:");
        cons.gridy = 1;
        cons.gridx = 0;
        this.add(accountLabel, cons);

        // ComboBox for Selecting Account
        accountComboBox = new JComboBox<>();
        cons.gridx = 1;
        
        this.add(accountComboBox, cons);

        // Label for Collateral
        JLabel collateralLabel = new JLabel("Collateral:");
        cons.gridy = 2;
        cons.gridx = 0;
        this.add(collateralLabel, cons);

        // TextField for Collateral
        JTextField collateralField = new JTextField(15);
        cons.gridx = 1;
        this.add(collateralField, cons);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        cons.gridy = 3;
        cons.gridx = 0;
        cons.gridwidth = 2; // Span across two columns
        cons.fill = GridBagConstraints.CENTER;
        SubLoanListener rll = new SubLoanListener(loanAmountField, accountComboBox, collateralField);
        submitButton.addActionListener(rll);
        this.add(submitButton, cons);

        loadAccountIds();

    }

    private void loadAccountIds() {
        String query = "SELECT AccountId FROM bankaccounts WHERE UserID = ?"; 
        
        
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Session.getInstance().getUserId()); // Set the UserID in the PreparedStatement
            try (ResultSet rs = stmt.executeQuery()) {
                accountComboBox.removeAllItems(); // Clear existing items
                while (rs.next()) {
                    String accountId = rs.getString("AccountId");
                    System.out.println(accountId);
                    accountComboBox.addItem(accountId);
                }
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
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}