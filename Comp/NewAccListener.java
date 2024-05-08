import java.awt.event.*;
import javax.swing.*;

class NewAccListener implements ActionListener{
    
    public NewAccListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "NewAcc Clicked" );
        UserDefPanel.changePanel(new NewAccountPanel());
    }
}