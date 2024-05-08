import java.awt.event.*;
import javax.swing.*;

class InternalTransferListener implements ActionListener{
    
    public InternalTransferListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "internal transfer Clicked" );
        UserDefPanel.changePanel(new InternalTransferPanel());
    }
}