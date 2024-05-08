import java.awt.event.*;
import javax.swing.*;

class DepositListener implements ActionListener{
    
    public DepositListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "DepositClicked" );
        UserDefPanel.changePanel(new DepositPanel());
    }
}