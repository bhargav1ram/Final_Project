import java.awt.event.*;
import javax.swing.*;

class TranWithdrawListener implements ActionListener{
    private JComboBox<String> accountComboBox;
    private JTextField depositAmountField;
    private JComboBox<String> currencyComboBox;
    
    public TranWithdrawListener(JComboBox<String> accountComboBox, JTextField depositAmountField, JComboBox<String> currencyComboBox) {
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
        
        LoanableAccount acc = new LoanableAccount(Session.getInstance().getUserId(), selectedAccount, Session.getInstance().getAccType(selectedAccount));
        acc.withdraw(selectedCurrency,Double.parseDouble(depositAmount));

        System.out.println( "Transactions Withdraws Clicked" );
        UserDefPanel.changePanel(new DepositPanel());
    }
}