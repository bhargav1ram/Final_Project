import java.awt.event.*;
import javax.swing.*;

class LogoutListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Logout Clicked" );
        Session.getInstance().logout();
        Project_UI.switchPanel(new LogoutPanel());
    }
}