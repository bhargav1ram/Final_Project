import java.awt.event.*;
import javax.swing.*;

class AdminLoginListener implements ActionListener{

    public AdminLoginListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Login Clicked" );
        Project_UI.switchPanel(new AdminDefPanel());
    }
}