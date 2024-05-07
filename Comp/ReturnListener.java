import java.awt.event.*;
import javax.swing.*;

class ReturnListener implements ActionListener{

    public ReturnListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Return Clicked" );
        Project_UI.switchPanel(Project_UI.lastPane);
    }
}