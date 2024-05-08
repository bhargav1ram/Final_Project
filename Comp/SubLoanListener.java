import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

class SubLoanListener implements ActionListener{
    private JTextField loanAmount;
    private JComboBox<String> accountComboBox;
    private JTextField collateral;
    
    public SubLoanListener(JTextField loanAmount, JComboBox<String> accountComboBox, JTextField collateral) {
        this.loanAmount = loanAmount;
        this.accountComboBox = accountComboBox;
        this.collateral = collateral;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println( "Sub Loan Clicked" );
        String selectedAccount = (String) accountComboBox.getSelectedItem();
        String amount = loanAmount.getText();
        String collateralText = collateral.getText();
        System.out.println("Loan Requested: Amount=" + amount + ", Account=" + selectedAccount + ", Collateral=" + collateralText);
        
        LoanableAccount lac = new LoanableAccount(Session.getInstance().getUserId(), selectedAccount, getAcctype(selectedAccount));
        lac.takeNewLoan(Double.parseDouble(amount),collateralText);
        UserDefPanel.changePanel(new ReqLoanPanel());
    }

    public String getAcctype(String AccID) {
        String query = "SELECT AccountType FROM bankaccounts WHERE UserID = ? AND AccountID = ?";
    
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, Session.getInstance().getUserId()); // Set the UserID in the PreparedStatement
            stmt.setString(2, AccID);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String acctype = rs.getString("AccountType");
                    System.out.println(acctype);
                    if (acctype.equals("1")) {
                        return "Savings";
                    }
                    return "Checkings";
                } else {
                    // Handle the case where no account is found
                    throw new IllegalStateException("No account found with the provided account ID.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Depending on how you want to handle the error,
            // you could return a default value or rethrow the exception
            throw new RuntimeException("Database error when fetching account type", ex);
        }
    }
}