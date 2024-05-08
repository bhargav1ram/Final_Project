import java.awt.event.*;
import javax.swing.*;

class TranTransferListener implements ActionListener{
    private JComboBox<String> toAccountComboBox;
    
    private JComboBox<String> fromAccountComboBox;

    private JTextField depositAmountField;
    
    public TranTransferListener(JComboBox<String> fromAccountComboBox,  JComboBox<String> toAccountComboBox, JTextField depositAmountField) {
        this.fromAccountComboBox= fromAccountComboBox;
        this.depositAmountField = depositAmountField;
        this.toAccountComboBox = toAccountComboBox;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String fromAccount = (String) fromAccountComboBox.getSelectedItem();
        String depositAmount = depositAmountField.getText();
        String toCurrency = (String) toAccountComboBox.getSelectedItem();

        System.out.println("Deposit Clicked: Amount=" + depositAmount + ", Account=" + fromAccountComboBox + ", to Account =" + toAccountComboBox);

        
        JOptionPane.showMessageDialog(null, "Deposit of " + depositAmount + " " + fromAccountComboBox + " to " + toAccountComboBox + " successful!");
        //Account acc = new Account(Session.getInstance().getUserId(), selectedAccount, Session.getInstance().getAccType(selectedAccount));
        //acc.deposit(selectedCurrency,Double.parseDouble(depositAmount));
        //System.out.println( "Transactions Deposits Clicked" );
        
        UserDefPanel.changePanel(new InternalTransferPanel());
    }
}