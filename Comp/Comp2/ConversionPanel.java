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


public class ConversionPanel extends JPanel{

    private BufferedImage backgroundImage;
    JComboBox<String> accountComboBox;

    public ConversionPanel(){
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


        // Label for Select Account
        JLabel accountLabel = new JLabel("Select Account:");
        cons.gridy = 0;
        cons.gridx = 0;
        this.add(accountLabel, cons);

        // ComboBox for Selecting Account
        accountComboBox = new JComboBox<>();
        cons.gridx = 1;
        
        this.add(accountComboBox, cons);


        JLabel label1 =  new JLabel("Enter Amount to Convert : ");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(label1,cons);

        JTextField tx1 = new JTextField();
        cons.gridx = 1;
        cons.gridy = 1;
        this.add(tx1,cons);
        
        // Label for Select Account
        JLabel iniCurLabel = new JLabel("Select Initial Currency:");
        cons.gridx = 0;
        cons.gridy = 2;
        this.add(iniCurLabel, cons);

        // ComboBox for Selecting Account
        JComboBox<String> iniCur = new JComboBox<>(new String[]{"INR", "USD", "EUR"});
        cons.gridx = 1;
        cons.gridy = 2;
        this.add(iniCur, cons);

        // Label for Select Account
        JLabel finCurLabel = new JLabel("Select Final Currency:");
        cons.gridx = 0;
        cons.gridy = 3;
        this.add(finCurLabel, cons);

        // ComboBox for Selecting Account
        JComboBox<String> finCur = new JComboBox<>(new String[]{"INR", "USD", "EUR"});
        cons.gridx = 1;
        cons.gridy = 3;
        this.add(finCur, cons);

        JButton convert = new JButton("Convert");
        cons.gridx = 0;
        cons.gridy = 4;
        this.add(convert, cons);
        TranConversionListener tcl = new TranConversionListener(accountComboBox,tx1,iniCur,finCur);
        convert.addActionListener(tcl);
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
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}