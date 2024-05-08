import java.awt.event.*;
import javax.swing.*;

class AdminSettingsListener implements ActionListener{

    public AdminSettingsListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminSettingsListener Clicked" );
        AdminDefPanel.shiftPanel(new AdminSettingsPanel());
    }
}