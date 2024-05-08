import java.awt.event.*;
import javax.swing.*;

class StockListener implements ActionListener{
    
    public StockListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "stocks Clicked" );
        UserDefPanel.changePanel(new StockPanel());
    }
}