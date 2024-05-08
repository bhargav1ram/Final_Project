import java.awt.event.*;
import javax.swing.*;

class AdminTradeListener implements ActionListener{

    public AdminTradeListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminTradeListener Clicked" );
        AdminDefPanel.shiftPanel(new AdminTradePanel());
    }
}