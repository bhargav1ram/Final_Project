import java.awt.event.*;
import javax.swing.*;

class AdminOfferListener implements ActionListener{

    public AdminOfferListener(){
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "AdminOfferListener Clicked" );
        AdminDefPanel.shiftPanel(new AdminOfferPanel());
    }
}