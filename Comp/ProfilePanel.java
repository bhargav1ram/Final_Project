import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProfilePanel extends JPanel {

    private BufferedImage backgroundImage;

    public ProfilePanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(20, 20, 20, 20); // Increased padding

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + Session.getInstance().getUserName() + "!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Bigger font size
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 2;
        cons.anchor = GridBagConstraints.CENTER; // Center alignment
        this.add(welcomeLabel, cons);

        // Resetting gridwidth for other components
        cons.gridwidth = 1;
        cons.anchor = GridBagConstraints.WEST; // Left alignment for labels and values

        // Name label and value
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 18)); // Slightly smaller font size for labels
        cons.gridy = 1;
        cons.gridx = 0;
        this.add(nameLabel, cons);
        JLabel name = new JLabel(Session.getInstance().getUserName());
        name.setFont(new Font("Serif", Font.PLAIN, 18));
        cons.gridx = 1;
        this.add(name, cons);

        // UserID label and value
        JLabel userIDLabel = new JLabel("UserID:");
        userIDLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        cons.gridy = 2;
        cons.gridx = 0;
        this.add(userIDLabel, cons);
        JLabel userID = new JLabel(Session.getInstance().getUserId());
        userID.setFont(new Font("Serif", Font.PLAIN, 18));
        cons.gridx = 1;
        this.add(userID, cons);

        // Role label and value
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        cons.gridy = 3;
        cons.gridx = 0;
        this.add(roleLabel, cons);
        JLabel role = new JLabel(Session.getInstance().getRole());
        role.setFont(new Font("Serif", Font.PLAIN, 18));
        cons.gridx = 1;
        this.add(role, cons);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}