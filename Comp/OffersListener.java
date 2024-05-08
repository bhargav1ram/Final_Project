import java.awt.event.*;
import javax.swing.*;

class OffersListener implements ActionListener{
    
    public OffersListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Offers Clicked" );
        UserDefPanel.changePanel(new OffersPanel());
    }
}