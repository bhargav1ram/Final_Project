/*
 * Accounts that can be loaned from. For example, we can't take a loan from
 * trading account. Both checkings and savings are loanable accounts.
 * One can only take loans in USD.
 */
import java.sql.*;
import java.util.*;

public class LoanableAccount extends Account implements AdminObserver {
    public LoanableAccount(String uid, String accId, double openingBalance, String accType){
        super(uid, accId, openingBalance, accType);
        loans = new ArrayList<>();
        // TODO: push current loans to the database??(When is this called)(Same loans updated if old list)//
    }

    public LoanableAccount(String uid, String accId, String accType){
        super(uid, accId, accType);
        // TODO: populate loans with previous loans from database??(Meaning is it like a get previous loans?)
        String sql = "SELECT LoanID, LoanAmount, InterestRate, LoanDate, Collateral, Currency FROM Loans WHERE AccountID = ?";

        try (Connection conn = Database.getConnection(); // Using the provided Database class for connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountId);  // Set the account ID parameter

            try (ResultSet rs = pstmt.executeQuery()) {


                while (rs.next()) {
                    int loanId = rs.getInt("LoanID");
                    double loanAmount = rs.getDouble("LoanAmount");
                    double interestRate = rs.getDouble("InterestRate");
                    String loanDate = String.valueOf(rs.getDate("LoanDate").toLocalDate());
                    String collateral = rs.getString("Collateral");
                    String currency = rs.getString("Currency");

                    //Print loans from here
                }

            }

        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }

    }

    protected List<Loan> loans; // loans

    // adds a new loan to the accont
    public void takeNewLoan(double amount, String collateral){
        loans.add(new Loan(amount, collateral));
        // TODO: add this loan to the database???(If this is for adding loans then what was 1st one for?)
        String sql = "INSERT INTO Loans (AccountID, LoanAmount, InterestRate, LoanDate, Collateral, Currency) VALUES (?, ?, ?, ?, ?, ?)";

        // Example interest rate and date
        double interestRate = Constants.get.interestRate; // Fixed interest rate for the example
        String loanDate = Clock.get.getTime(); // Current date as loan date

        try (Connection conn = Database.getConnection(); // Using the provided Database class for connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountId);
            pstmt.setDouble(2, amount);
            pstmt.setDouble(3, interestRate);
            pstmt.setDate(4, java.sql.Date.valueOf(loanDate));
            pstmt.setString(5, collateral);
            pstmt.setString(6, Constants.get.usdSymbol);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Loan successfully added to the database.");
            } else {
                System.out.println("Failed to add the loan to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // get loans in string format
    public List<String> getLoansDesc() {
        List<String> lns = new ArrayList<>();
        if(loans!=null) {
            for (Loan loan : loans) {
                lns.add(loan.toString());
            }
        }
        return lns;
    }

    // if this account has been defaulted loans
    public boolean hasDefaultedLoan(){
        boolean defaulted = false;
        for (Loan loan : loans) {
            if (loan.isDefaulted()) {
                defaulted = true;
            }
        }
        return defaulted;
    }

    // pay a particular loan. 0 index is loanId
    public void payOffLoan(int loanId, double amount){
        deductFee(Constants.get.usdSymbol, amount*Constants.get.feePercent);
        amount *= (1.0-Constants.get.feePercent);
        Loan curLoan = loans.get(loanId);
        curLoan.decreaseAmount(amount);
        if (curLoan.getAmount()==0.0) {
            loans.remove(curLoan);
        }
        // TODO: update the database with right loans(Reduce loan balance?)
        decreaseBalance(Constants.get.usdSymbol, amount);
        addToTransactions(new Transaction("account", "loan "+loanId, amount, Clock.get.getTime()));
    }

    // pay interest from balance to admin or else mark loan as defaulted
    protected void payInterestFromBalance(){
        int loanId = 0;
        for (Loan loan : loans) {
            double years = Clock.get.getNumOfYearsInDecimal(loan.getStartTime(), Clock.get.getTime());
            double interest = Constants.get.interestRate*years*loan.getAmount();

            // decrease that amount from balance and add to defaulted payments if interest is more
            if (getBalance(Constants.get.usdSymbol) >= interest) {
                decreaseBalance(Constants.get.usdSymbol, interest);
                addToTransactions(new Transaction("account", "interest to loan "+loanId, interest, Clock.get.getTime()));
            }
            else{
                double curbalance = getBalance(Constants.get.usdSymbol);
                decreaseBalance(Constants.get.usdSymbol, curbalance);
                addToTransactions(new Transaction("account", "interest to loan "+loanId, curbalance, Clock.get.getTime()));
                loan.addToDefaultedPayments(interest-curbalance);
                // TODO: update the database with right loans???(When is this used?)
            }

            loanId += 1;
        }
    }

    // get balance that can be withdrawn from ATM at this point
    public double getWithdrawableBalance(String symbol){
        double balance = getBalance(symbol);
        if (symbol == Constants.get.usdSymbol) {
            balance = balance-minBalance;
            if (balance<0.0) balance = 0.0;
        }
        return balance/(1+Constants.get.feePercent);
    }

    // withdraw from ATM
    public void withdraw(String symbol, double amount){
        deductFee(symbol, amount*Constants.get.feePercent);
        decreaseBalance(symbol, amount);
        addToTransactions(new Transaction("account", "withdrawal", amount*Currencies.get.getCurrency(symbol).getExchangeRate(), Clock.get.getTime()));
    }

    // convert from currency to another currency. Convert 100 EUR to x USD
    public void convert(String fromSymbol, String toSymbol, double amount){
        deductFee(fromSymbol, amount*Constants.get.feePercent);
        amount *= (1.0-Constants.get.feePercent);
        decreaseBalance(fromSymbol, amount);
        addBalance(toSymbol, amount*Currencies.get.getCurrency(toSymbol).getExchangeRate()/Currencies.get.getCurrency(fromSymbol).getExchangeRate());
        addToTransactions(new Transaction(fromSymbol, toSymbol, amount*Currencies.get.getCurrency(fromSymbol).getExchangeRate(), Clock.get.getTime()));
    }

    public void getUpdateFromAdmin() {
        payInterestFromBalance();
    }
}
