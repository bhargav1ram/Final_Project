import java.awt.event.*;
import javax.swing.*;

class AdminDispTranListener implements ActionListener{

    public AdminDispTranListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminDispTranListener Clicked" );
        AdminDefPanel.shiftPanel(new DisplayTransactionsPanel());
    }
}