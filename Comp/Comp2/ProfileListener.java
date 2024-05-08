import java.awt.event.*;
import javax.swing.*;

class ProfileListener implements ActionListener{
    
    public ProfileListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Profile Clicked" );
        UserDefPanel.changePanel(new ProfilePanel());
    }
}