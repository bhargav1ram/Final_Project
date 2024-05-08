import java.awt.event.*;
import javax.swing.*;

class DisplayListener implements ActionListener{
    
    public DisplayListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Display Clicked" );
        UserDefPanel.changePanel(new DisplayPanel());
    }
}