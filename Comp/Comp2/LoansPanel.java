import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;


public class LoansPanel extends JPanel{

    private BufferedImage backgroundImage;

    public LoansPanel(){
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\pbhar\\OneDrive\\Desktop\\Code\\Java-8\\Swing\\pic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setLayout(new GridBagLayout());
        
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10,10,10);

        JButton prevLoan = new JButton("Previous Loans");
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        this.add(prevLoan,cons);
        PrevLoanListener pll = new PrevLoanListener();
        prevLoan.addActionListener(pll);

        
        JButton currLoan = new JButton("Ongoing/Current Loans");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.anchor = GridBagConstraints.WEST;
        this.add(currLoan,cons);
        CurrLoanListener cll = new CurrLoanListener();
        currLoan.addActionListener(cll);

        JButton reqLoan = new JButton("Request a Loan");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.WEST;
        this.add(reqLoan,cons);
        ReqLoanListener rll = new ReqLoanListener();
        reqLoan.addActionListener(rll);
        
        

    }

}