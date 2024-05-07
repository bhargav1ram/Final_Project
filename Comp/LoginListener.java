import java.awt.event.*;
import javax.swing.*;

class LoginListener implements ActionListener{

    public LoginListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Login Clicked" );
        //Database sending details
        
        Project_UI.switchPanel(new UserDefPanel());
    }
}