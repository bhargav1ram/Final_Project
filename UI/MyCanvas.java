import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;  

public class MyCanvas extends Canvas{  
      
    public void paint(Graphics g) {  
  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("pic.jpg");  
        g.drawImage(i, 0,0,this);  
          
    }  
        public static void main(String[] args) {  
        MyCanvas m=new MyCanvas();  
        JFrame f=new JFrame();  
        f.add(m);  
        JPanel p1 =new JPanel();
        JButton b1 = new JButton("Big Button");
        b1.setSize(10,20);
        p1.add(b1);
        f.add(p1);
        f.setSize(400,400);  
        f.setVisible(true);  

    }  
  
}  