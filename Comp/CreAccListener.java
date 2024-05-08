import java.awt.event.*;
import javax.swing.*;

class CreAccListener implements ActionListener{

    public CreAccListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "CreAcc Clicked" );
        Project_UI.switchPanel(new CreAccPanel());
    }
}