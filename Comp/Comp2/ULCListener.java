import java.awt.event.*;
import javax.swing.*;

class ULCListener implements ActionListener{
    JPanel pane;

    public ULCListener(JPanel pane){
        this.pane = pane;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "User Clicked" );
        Project_UI.switchPanel(pane);
    }
}