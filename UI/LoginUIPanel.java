import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginUIPanel extends JPanel{
    
    private BufferedImage backgroundImage;

    public LoginUIPanel(){
        this.setLayout(null);

        try{
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic1.jpg"));
        }catch(Exception e){
            e.printStackTrace();
        }
        ULCListener u = new ULCListener(new LoginPanel());
        JButton user = new JButton("Login as an User");
        user.setBounds(700,300,200,50);
        user.addActionListener(u);
            
        ALCListener a = new ALCListener(new AdminLoginPanel());
        JButton admin = new JButton("Login as an Admin");
        admin.setBounds(950,300,200,50);
        admin.addActionListener(a);


        this.add(user);
        this.add(admin);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(backgroundImage!=null){
            g.drawImage(backgroundImage,0,0,backgroundImage.getWidth(),backgroundImage.getHeight(),this);
        }
    }
    
    
}