import java.awt.event.*;
import javax.swing.*;

class LoginListener implements ActionListener{

    public LoginListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Login Clicked" );
        Project_UI.switchPanel(new UserDefPanel());
    }
}