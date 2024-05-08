import java.awt.event.*;
import javax.swing.*;

class ReqLoanListener implements ActionListener{
    
    public ReqLoanListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Request Loans Clicked" );
        UserDefPanel.changePanel(new ReqLoanPanel());
    }
}