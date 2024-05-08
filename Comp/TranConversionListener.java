import java.awt.event.*;
import javax.swing.*;

class TranConversionListener implements ActionListener{
    private JComboBox<String> accountComboBox;
    private JComboBox<String> iniCur;
    private JTextField depositAmountField;
    private JComboBox<String> finCur;
    
    public TranConversionListener(JComboBox<String> accountComboBox, JTextField depositAmountField, JComboBox<String> iniCur, JComboBox<String> finCur) {
        this.accountComboBox = accountComboBox;
        this.depositAmountField = depositAmountField;
        this.iniCur = iniCur;
        this.finCur = finCur;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String selectedAccount = (String) accountComboBox.getSelectedItem();
        String depositAmount = depositAmountField.getText();
        String initCurrency = (String) iniCur.getSelectedItem();
        String finalCurrency = (String) finCur.getSelectedItem();

        System.out.println("Deposit Clicked: Amount=" + depositAmount + ", Account=" + selectedAccount + ", Currency=" + initCurrency+"Final Curr"+finalCurrency );

        
        JOptionPane.showMessageDialog(null, "Deposit of "+initCurrency +" "+ depositAmount +" " + finalCurrency+ " " + " to " + selectedAccount + " successful!");
        LoanableAccount acc = new LoanableAccount(Session.getInstance().getUserId(), selectedAccount, Session.getInstance().getAccType(selectedAccount));
        acc.convert(initCurrency,finalCurrency,Double.parseDouble(depositAmount));
        System.out.println( "Transactions Converts Clicked" );
        
        UserDefPanel.changePanel(new ConversionPanel());
    }
}