import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UserDefPanel extends JPanel{

    private BufferedImage backgroundImage;

    public UserDefPanel(){
        this.setLayout(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel menu = new JPanel(new GridBagLayout());
        //Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        //Border line = BorderFactory.createLineBorder(Color.BLACK);
        
        menu.setSize(1800,40);
        menu.setLocation(50, 100);
        //menu.setBorder(BorderFactory.createCompoundBorder(line,pad));
        this.add(menu);

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        //cons.insets = new Insets(10, 10,10,10);
        cons.weightx = 1.0;
        cons.weighty = 1.0;

        MJButton prof = new MJButton("Profile");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(prof,cons);
       
        MJButton accSum = new MJButton("Account Summary");
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(accSum,cons);
       
        MJButton loan = new MJButton("Loans");
        cons.gridx = 2;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(loan,cons);

        MJButton tran = new MJButton("Transactions");
        cons.gridx = 3;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(tran,cons);

        MJButton offers = new MJButton("Offers");
        cons.gridx = 4;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(offers,cons);

        MJButton trade = new MJButton("Trade");
        
        cons.gridx = 5;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(trade,cons);

        JButton back= new JButton("Back");
        back.setBounds(10,0,100,35);
        ReturnListener backl = new ReturnListener();
        back.addActionListener(backl);
        this.add(back);

        JButton home = new JButton("home");
        home.setBounds(120,0,100,35);
        HomeListener homel = new HomeListener();
        home.addActionListener(homel);
        this.add(home);

        JPanel page = new JPanel(new GridBagLayout());
        Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        Border line = BorderFactory.createLineBorder(Color.BLACK);

        page.setSize(1550,800);
        page.setLocation(200, 200);
        page.setBorder(BorderFactory.createCompoundBorder(line,pad));
        this.add(page);

        ProfilePanel profPage = new ProfilePanel();
        page.add(profPage);
       
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }
}
/* 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class UserDefPanel extends JPanel {

    private BufferedImage backgroundImage;
    private JPanel menu;
    private JPanel page;
    private CardLayout cardLayout;

    private HashMap<String, JPanel> panelsMap;

    public UserDefPanel() {
        this.setLayout(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the panels
        menu = createMenuPanel();
        page = createPagePanel();
        cardLayout = new CardLayout();
        page.setLayout(cardLayout);

        // Add panels to the main panel
        this.add(menu);
        this.add(page);

        // Initialize the panels map
        panelsMap = new HashMap<>();
        panelsMap.put("Profile", new Profile());
        // Add more panels to the panels map as needed

        // Add listeners to the menu buttons to switch panels
        for (Component comp : menu.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String panelName = button.getText();
                        cardLayout.show(page, panelName);
                    }
                });
            }
        }
    }

    // Create the menu panel
    private JPanel createMenuPanel() {
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setSize(1920, 30);
        menu.setLocation(0, 100);

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);

        JButton prof = new JButton("Profile");
        menu.add(prof, cons);

        JButton accSum = new JButton("Account Summary");
        menu.add(accSum, cons);

        JButton loan = new JButton("Loans");
        menu.add(loan, cons);

        JButton tran = new JButton("Transactions");
        menu.add(tran, cons);

        return menu;
    }

    // Create the page panel
    private JPanel createPagePanel() {
        JPanel page = new JPanel();
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        page.setBorder(BorderFactory.createCompoundBorder(line, pad));
        page.setSize(1550, 800);
        page.setLocation(200, 200);
        return page;
    }

    // Override paintComponent to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }
}*/