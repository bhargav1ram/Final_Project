import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreAccPanel extends JPanel {

    private BufferedImage backgroundImage;

    public CreAccPanel() {
        this.setLayout(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("pic2.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel box = new JPanel(new GridBagLayout());
        Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        
        box.setSize(500, 800);
        box.setLocation(650, 100);
        box.setBorder(BorderFactory.createCompoundBorder(line, pad));
        this.add(box);

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);

        // Adding label and field for User Name
        JLabel nameLabel = new JLabel("Name:");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        box.add(nameLabel, cons);

        JTextField nameField = new JTextField(20);
        cons.gridx = 1;
        cons.gridy = 0;
        box.add(nameField, cons);

        // Adding label and field for User ID
        JLabel userIdLabel = new JLabel("User ID:");
        cons.gridx = 0;
        cons.gridy = 1;
        box.add(userIdLabel, cons);

        JTextField userIdField = new JTextField(20);
        cons.gridx = 1;
        cons.gridy = 1;
        box.add(userIdField, cons);

        // Adding label and field for Password
        JLabel passwordLabel = new JLabel("Password:");
        cons.gridx = 0;
        cons.gridy = 2;
        box.add(passwordLabel, cons);

        JPasswordField passwordField = new JPasswordField(20);
        cons.gridx = 1;
        cons.gridy = 2;
        box.add(passwordField, cons);

        // Adding label and field for Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        cons.gridx = 0;
        cons.gridy = 3;
        box.add(confirmPasswordLabel, cons);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        cons.gridx = 1;
        cons.gridy = 3;
        box.add(confirmPasswordField, cons);

        // Adding label and combo box for Role
        JLabel roleLabel = new JLabel("Role:");
        cons.gridx = 0;
        cons.gridy = 4;
        box.add(roleLabel, cons);

        String[] roles = {"User", "Admin"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        cons.gridx = 1;
        cons.gridy = 4;
        box.add(roleCombo, cons);

        // Adding submit button
        JButton submitButton = new JButton("Create Account");
        cons.gridx = 0;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.anchor = GridBagConstraints.CENTER;
        box.add(submitButton, cons);
        CreatedAccountListener cal = new CreatedAccountListener(nameField,userIdField,passwordField,confirmPasswordField,roleCombo);
        submitButton.addActionListener(cal);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 0, 100, 35);
        ReturnListener rl = new ReturnListener();
        backButton.addActionListener(rl);
        this.add(backButton);

        // Home button, assuming it returns to the main menu
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(120, 0, 100, 35);
        HomeListener hom = new HomeListener();
        homeButton.addActionListener(hom);
        this.add(homeButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}