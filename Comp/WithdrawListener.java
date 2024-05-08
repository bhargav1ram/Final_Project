import java.awt.event.*;
import javax.swing.*;

class WithdrawListener implements ActionListener{
    
    public WithdrawListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Withdraw Clicked" );
        UserDefPanel.changePanel(new WithdrawPanel());
    }
}