import java.awt.event.*;
import javax.swing.*;

class PrevLoanListener implements ActionListener{
    
    public PrevLoanListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Prev Loans Clicked" );
        UserDefPanel.changePanel(new PrevLoanPanel());
    }
}