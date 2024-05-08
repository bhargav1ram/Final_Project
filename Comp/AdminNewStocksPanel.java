import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class AdminNewStocksPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JTextField companyNameTextField;
    private JTextField symbolTextField;
    private JTextField currentPriceTextField;
    private JComboBox<String> currencyComboBox;
    private JButton submitButton;

    public AdminNewStocksPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Company Name
        add(new JLabel("Company Name:"), gbc);
        companyNameTextField = new JTextField(20);
        gbc.gridx = 1;
        add(companyNameTextField, gbc);

        // Symbol
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Symbol:"), gbc);
        symbolTextField = new JTextField(10);
        gbc.gridx = 1;
        add(symbolTextField, gbc);

        // Current Price
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Current Price:"), gbc);
        currentPriceTextField = new JTextField(10);
        gbc.gridx = 1;
        add(currentPriceTextField, gbc);

        // Currency Dropdown
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Currency:"), gbc);
        String[] currencies = {"INR", "USD", "EUR"};
        currencyComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        add(currencyComboBox, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        // Set action listener for the submit button
        submitButton.addActionListener(e -> submitStockData());
    }

    private void submitStockData() {
        // Logic to handle the submission of new stock data
        String companyName = companyNameTextField.getText();
        String symbol = symbolTextField.getText();
        String price = currentPriceTextField.getText();
        String currency = (String) currencyComboBox.getSelectedItem();

        System.out.println("Submitting new stock:");
        System.out.println("Company Name: " + companyName);
        System.out.println("Symbol: " + symbol);
        System.out.println("Price: " + price + " " + currency);

        // Add database or logic to store this information
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}