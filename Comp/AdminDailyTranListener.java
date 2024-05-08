import java.awt.event.*;
import javax.swing.*;

class AdminDailyTranListener implements ActionListener{

    public AdminDailyTranListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminDailyTranListener Clicked" );
        AdminDefPanel.shiftPanel(new DailyTransactionsPanel());
    }
}