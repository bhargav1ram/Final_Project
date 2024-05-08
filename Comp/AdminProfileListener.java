import java.awt.event.*;
import javax.swing.*;

class AdminProfileListener implements ActionListener{

    public AdminProfileListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminProfileListener Clicked" );
        AdminDefPanel.shiftPanel(new AdminProfilePanel());
    }
}