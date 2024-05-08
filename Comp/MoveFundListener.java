import java.awt.event.*;
import javax.swing.*;

class MoveFundListener implements ActionListener{
    
    public MoveFundListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Move Funds Clicked" );
        UserDefPanel.changePanel(new MoveFundPanel());
    }
}