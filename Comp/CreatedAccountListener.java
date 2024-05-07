import java.awt.event.*;
import javax.swing.*;

class CreatedAccountListener implements ActionListener {
    private JTextField nameField;
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleCombo;

    public CreatedAccountListener(){}

    // Constructor to pass in the fields from the panel
    public CreatedAccountListener(JTextField nameField, JTextField userIdField, JPasswordField passwordField, JPasswordField confirmPasswordField, JComboBox<String> roleCombo) {
        this.nameField = nameField;
        this.userIdField = userIdField;
        this.passwordField = passwordField;
        this.confirmPasswordField = confirmPasswordField;
        this.roleCombo = roleCombo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String userId = userIdField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();

        System.out.println(name+" "+userId+" "+password+" "+confirmPassword+" "+role);

        // Check if the password fields match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BankUser bu = new BankUser(name, userId, password, role);
        // Assuming createNew is a static method in BankUser class that returns a boolean
        boolean result = bu.createNew();
        if (result) {
            JOptionPane.showMessageDialog(null, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Possibly clear fields or switch panels
        } else {
            JOptionPane.showMessageDialog(null, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}