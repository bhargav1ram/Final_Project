import java.awt.event.*;
import javax.swing.*;

class SelAccListener implements ActionListener{
    
    public SelAccListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "SelAcc Clicked" );
        UserDefPanel.changePanel(new SelUserAccPanel());
    }
}