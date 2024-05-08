import java.awt.event.*;
import javax.swing.*;

class NewAccSubmitListener implements ActionListener{
    private JRadioButton currentAccount;
    private JRadioButton savingsAccount;
    private JTextField amountField;

    public NewAccSubmitListener(JRadioButton currentAccount, JRadioButton savingsAccount, JTextField amountField){
        this.currentAccount = currentAccount;
        this.savingsAccount = savingsAccount;
        this.amountField = amountField;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String accountType = currentAccount.isSelected() ? "Checkings" : savingsAccount.isSelected() ? "Savings" : "";
        String amount = amountField.getText();

        String accId = Session.getInstance().getUserId() + "-"+accountType;

        if(accountType.equals("Checkings")){
            
            CheckingsAccount acc = new CheckingsAccount(Session.getInstance().getUserId(), accId, Double.parseDouble(amount), accountType);
        }else{
            SavingsAccount acc = new SavingsAccount(Session.getInstance().getUserId(), accId, Double.parseDouble(amount), accountType);
        }
        System.out.println("Account Type: " + accountType);
        System.out.println("Amount: " + amount);
        System.out.println( "NewAccSubmit Clicked" );

        UserDefPanel.changePanel(new NewAccountPanel());
    }
}