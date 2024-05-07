import java.awt.event.*;
import javax.swing.*;

class HomeListener implements ActionListener{

    public HomeListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Return Clicked" );
        Project_UI.switchPanel(new LoginUIPanel());
    }
}