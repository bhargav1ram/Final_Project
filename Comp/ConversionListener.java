import java.awt.event.*;
import javax.swing.*;

class ConversionListener implements ActionListener{
    
    public ConversionListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Convo Clicked" );
        UserDefPanel.changePanel(new ConversionPanel());
    }
}