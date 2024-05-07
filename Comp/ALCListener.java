import java.awt.event.*;
import javax.swing.*;

class ALCListener implements ActionListener{
    JPanel pane;

    public ALCListener(JPanel pane){
        this.pane = pane;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Admin Clicked" );
        Project_UI.switchPanel(pane);
    }
}