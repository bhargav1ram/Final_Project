import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;



public class InternalTransferPanel extends JPanel {
    private BufferedImage backgroundImage;

    public InternalTransferPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);

        // Setup constraints
        cons.anchor = GridBagConstraints.WEST;

        // Amount input
        JLabel amountLabel = new JLabel("Enter Amount:");
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(amountLabel, cons);

        JTextField amountField = new JTextField(10);
        cons.gridx = 1;
        this.add(amountField, cons);

        // Account From selection
        JLabel fromAccountLabel = new JLabel("Account From:");
        cons.gridy = 1; // Move to the next row
        cons.gridx = 0;
        this.add(fromAccountLabel, cons);

        JComboBox<String> fromAccountComboBox = new JComboBox<>(new String[] {"Account 1", "Account 2", "Account 3"});
        cons.gridx = 1;
        this.add(fromAccountComboBox, cons);

        // Account To selection
        JLabel toAccountLabel = new JLabel("Account To:");
        cons.gridy = 2; // Move to the next row
        cons.gridx = 0;
        this.add(toAccountLabel, cons);

        JComboBox<String> toAccountComboBox = new JComboBox<>(new String[] {"Account 1", "Account 2", "Account 3"});
        cons.gridx = 1;
        this.add(toAccountComboBox, cons);

        // Transfer button
        JButton transferButton = new JButton("Transfer");
        cons.gridy = 3; // Move to the next row
        cons.gridx = 0;
        cons.gridwidth = 2; // Span across two columns
        this.add(transferButton, cons);

        // Set action listeners as required for functionality
        // Example: transferButton.addActionListener(e -> performTransfer());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}