import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;

public class AdminSettingsPanel extends JPanel {
    private BufferedImage backgroundImage;
  

    public AdminSettingsPanel() {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        // Label "Set Calendar"
        JLabel setCalendarLabel = new JLabel("Set Calendar:");
        this.add(setCalendarLabel, gbc);

        // Day ComboBox
        String[] days = new String[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = Integer.toString(i + 1);
        }
        JComboBox dayComboBox = new JComboBox<>(days);
        gbc.gridx = 1;
        this.add(dayComboBox, gbc);

        // Month ComboBox
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox monthComboBox = new JComboBox<>(months);
        gbc.gridx = 2;
        this.add(monthComboBox, gbc);

        // Year ComboBox
        String[] years = new String[50];  // Example range from 1970 to 2019
        for (int i = 0; i < years.length; i++) {
            years[i] = Integer.toString(1970 + i);
        }
        JComboBox yearComboBox = new JComboBox<>(years);
        gbc.gridx = 3;
        this.add(yearComboBox, gbc);
        

        
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}