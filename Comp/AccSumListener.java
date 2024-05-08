import java.awt.event.*;
import javax.swing.*;

class AccSumListener implements ActionListener{
    
    public AccSumListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AccSum Clicked" );
        UserDefPanel.changePanel(new AccSumPanel());
    }
}