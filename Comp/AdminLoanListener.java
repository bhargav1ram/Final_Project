import java.awt.event.*;
import javax.swing.*;

class AdminLoanListener implements ActionListener{

    public AdminLoanListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminLoanListener Clicked" );
        AdminDefPanel.shiftPanel(new AdminLoanPanel());
    }
}