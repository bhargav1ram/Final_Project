import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UserDefPanel extends JPanel{

    private BufferedImage backgroundImage;
    private static JPanel page;
    private static JPanel curPanel;

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
        ProfileListener pol = new ProfileListener();
        prof.addActionListener(pol);
       
        MJButton accSum = new MJButton("Account Summary");
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(accSum,cons);
        AccSumListener acsl = new AccSumListener();
        accSum.addActionListener(acsl);

        MJButton accounts = new MJButton("Accounts");
        cons.gridx = 2;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(accounts,cons);
        AccountsListener acl = new AccountsListener();
        accounts.addActionListener(acl);
       
        MJButton loan = new MJButton("Loans");
        cons.gridx = 3;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(loan,cons);
        LoansListener loal = new LoansListener();
        loan.addActionListener(loal);

        MJButton tran = new MJButton("Transactions");
        cons.gridx = 4;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(tran,cons);
        TransactionsListener tral = new TransactionsListener();
        tran.addActionListener(tral);

        MJButton offers = new MJButton("Offers");
        cons.gridx = 5;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(offers,cons);
        OffersListener offl = new OffersListener();
        offers.addActionListener(offl);

        MJButton trade = new MJButton("Trade");
        
        cons.gridx = 6;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        menu.add(trade,cons);
        TradeListener trl = new TradeListener();
        trade.addActionListener(trl);

        JButton back= new JButton("Back");
        back.setBounds(10,0,100,35);
        ReturnListener backl = new ReturnListener();
        back.addActionListener(backl);
        this.add(back);

        JButton home = new JButton("Home");
        home.setBounds(120,0,100,35);
        HomeListener homel = new HomeListener();
        home.addActionListener(homel);
        this.add(home);

        JButton logout = new JButton("Logout");
        logout.setBounds(1780,0,100,35);
        LogoutListener lol = new LogoutListener();
        logout.addActionListener(lol);
        this.add(logout);
        
        page = new JPanel();
        Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        Border line = BorderFactory.createLineBorder(Color.BLACK);

        page.setSize(1550,800);
        page.setLocation(200, 200);
        page.setBorder(BorderFactory.createCompoundBorder(line,pad));
        this.add(page);

        curPanel = new ProfilePanel();
            
        page.add(curPanel);
       
    }

    public static void changePanel(JPanel jp){
        if(page!=null){
            page.remove(curPanel);
        }
        curPanel = jp;
        page.add(curPanel);
        page.revalidate();
        page.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }
}

