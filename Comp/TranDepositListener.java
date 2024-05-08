import java.awt.event.*;
import javax.swing.*;

class TranDepositListener implements ActionListener{
    private JComboBox<String> accountComboBox;
    private JTextField depositAmountField;
    private JComboBox<String> currencyComboBox;
    
    public TranDepositListener(JComboBox<String> accountComboBox, JTextField depositAmountField, JComboBox<String> currencyComboBox) {
        this.accountComboBox = accountComboBox;
        this.depositAmountField = depositAmountField;
        this.currencyComboBox = currencyComboBox;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String selectedAccount = (String) accountComboBox.getSelectedItem();
        String depositAmount = depositAmountField.getText();
        String selectedCurrency = (String) currencyComboBox.getSelectedItem();

        System.out.println("Deposit Clicked: Amount=" + depositAmount + ", Account=" + selectedAccount + ", Currency=" + selectedCurrency);

        
        JOptionPane.showMessageDialog(null, "Deposit of " + depositAmount + " " + selectedCurrency + " to " + selectedAccount + " successful!");
        Account acc = new Account(Session.getInstance().getUserId(), selectedAccount, Session.getInstance().getAccType(selectedAccount));
        acc.deposit(selectedCurrency,Double.parseDouble(depositAmount));
        System.out.println( "Transactions Deposits Clicked" );
        UserDefPanel.changePanel(new DepositPanel());
    }
}