import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ReqLoanPanel extends JPanel{

    private BufferedImage backgroundImage;

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
        JComboBox<String> accountComboBox = new JComboBox<>(new String[]{"Account 1", "Account 2", "Account 3"});
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
        this.add(submitButton, cons);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}