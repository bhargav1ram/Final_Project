import java.awt.event.*;
import javax.swing.*;

class LoansListener implements ActionListener{
    
    public LoansListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Loans Clicked" );
        UserDefPanel.changePanel(new LoansPanel());
    }
}