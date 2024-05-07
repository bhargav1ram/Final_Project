import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginPanel extends JPanel{

    private BufferedImage backgroundImage;

    public LoginPanel(){
        this.setLayout(null);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel box = new JPanel(new GridBagLayout());
        Border pad = BorderFactory.createEmptyBorder(10,10,10,10);
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        
        box.setSize(500,800);
        box.setLocation(650, 100);
        box.setBorder(BorderFactory.createCompoundBorder(line,pad));
        this.add(box);

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10,10,10);


        //box.setLayout(null);
        JLabel userLabel = new JLabel("User");
        
        //userLabel.setBounds(30,10,200,30);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        box.add(userLabel,cons);


        JTextField userName = new JTextField();
        //userName.setBounds(900,350,200,30);
        cons.gridx = 1;
        cons.gridy = 0;
        box.add(userName,cons);


        JLabel passwordLabel = new JLabel("Password");
        //passwordLabel.setBounds(800,400,200,30);
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        box.add(passwordLabel,cons);

        JPasswordField password = new JPasswordField();
        //password.setBounds(900,400,200,30);
        cons.gridx = 1;
        cons.gridy = 1;
        box.add(password,cons);
        

        JButton login = new JButton("Login");
        //login.setBounds(840,500,150,35);
        //ImageIcon icon = new ImageIcon("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic1.jpg");
        //login.setIcon(icon);
        cons.gridx = 0;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.EAST;
        box.add(login,cons);
        LoginListener ll = new LoginListener();
        login.addActionListener(ll);
        

        JLabel ques = new JLabel("Don't have an account ??");
        //ques.setBounds(800,600,200,30);
        cons.gridx = 0;
        cons.gridy = 3;
        cons.anchor = GridBagConstraints.WEST;
        box.add(ques,cons);

        JButton creaAcc = new JButton("Create Account");
        //creaAcc.setBounds(840,650,150,35);
        cons.gridx = 1;
        cons.gridy = 3;
        CreAccListener cal = new CreAccListener();
        creaAcc.addActionListener(cal);
        box.add(creaAcc,cons);


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
        


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, backgroundImage.getWidth(), backgroundImage.getHeight(), this);
        }
    }
}