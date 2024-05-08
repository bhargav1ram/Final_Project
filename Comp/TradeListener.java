import java.awt.event.*;
import javax.swing.*;

class TradeListener implements ActionListener{
    
    public TradeListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Trade Clicked" );
        UserDefPanel.changePanel(new TradePanel());
    }
}