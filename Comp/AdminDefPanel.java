import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AdminDefPanel extends JPanel{

    private BufferedImage backgroundImage;

    public AdminDefPanel(){
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
        
        menu.setSize(200,900);
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
       
        MJButton accSum = new MJButton("Accounts/Users");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(accSum,cons);
       
        MJButton loan = new MJButton("Loans");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(loan,cons);

        MJButton tran = new MJButton("Transactions");
        cons.gridx = 0;
        cons.gridy = 3;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(tran,cons);

        MJButton offers = new MJButton("Offers");
        cons.gridx = 0;
        cons.gridy = 4;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(offers,cons);

        MJButton trade = new MJButton("Trade");
        cons.gridx = 0;
        cons.gridy = 5;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(trade,cons);

        MJButton settings = new MJButton("Settings");
        cons.gridx = 0;
        cons.gridy = 6;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(settings,cons);

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

        page.setSize(1550,900);
        page.setLocation(300, 100);
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