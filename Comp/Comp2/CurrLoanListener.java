import java.awt.event.*;
import javax.swing.*;

class CurrLoanListener implements ActionListener{
    
    public CurrLoanListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Curr Loans Clicked" );
        UserDefPanel.changePanel(new CurrLoanPanel());
    }
}