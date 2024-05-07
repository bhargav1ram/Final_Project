import java.awt.event.*;
import javax.swing.*;

class LogoutListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Logout Clicked" );
        Project_UI.switchPanel(new LogoutPanel());
    }
}