import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class AdminXchgRatePanel extends JPanel {
    private BufferedImage backgroundImage;
    private JComboBox<String> currencyComboBox;
    private JTextField exchangeRateTextField;
    private JButton submitButton;

    public AdminXchgRatePanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        initializeComponents(gbc);
    }

    private void initializeComponents(GridBagConstraints gbc) {
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Label for currency selection
        add(new JLabel("Select Currency:"), gbc);

        // Currency Dropdown
        String[] currencies = {"INR", "EUR", "USD"};
        currencyComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        add(currencyComboBox, gbc);

        // Label for exchange rate input
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Exchange Rate in USD:"), gbc);

        // Text field for exchange rate
        exchangeRateTextField = new JTextField(10);
        gbc.gridx = 1;
        add(exchangeRateTextField, gbc);

        // Submit button
        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        // Action listener for the submit button
        submitButton.addActionListener(this::submitExchangeRate);
    }

    private void submitExchangeRate(ActionEvent e) {
        // Logic to handle the submission of new exchange rate
        String currency = (String) currencyComboBox.getSelectedItem();
        String rate = exchangeRateTextField.getText();
        System.out.println("Submitting new exchange rate:");
        System.out.println("Currency: " + currency);
        System.out.println("Rate: " + rate + " USD");

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