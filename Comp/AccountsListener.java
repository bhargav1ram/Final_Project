import java.awt.event.*;
import javax.swing.*;

class AccountsListener implements ActionListener{
    
    public AccountsListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Accounts Clicked" );
        UserDefPanel.changePanel(new AccountsPanel());
    }
}