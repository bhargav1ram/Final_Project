import java.awt.event.*;
import javax.swing.*;

class AdminDisplayUserAccountsListener implements ActionListener{

    public AdminDisplayUserAccountsListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminDisplayUserAccountsListener Clicked" );
        AdminDefPanel.shiftPanel(new DisplayUserAccountsPanel());
    }
}