import java.awt.event.*;
import javax.swing.*;

class TransactionsListener implements ActionListener{
    
    public TransactionsListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Transactions Clicked" );
        UserDefPanel.changePanel(new TransactionsPanel());
    }
}