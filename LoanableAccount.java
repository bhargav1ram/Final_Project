/*
 * Accounts that can be loaned from. For example, we can't take a loan from
 * trading account. Both checkings and savings are loanable accounts.
 * One can only take loans in USD.
 */
import java.util.*;

public class LoanableAccount extends Account implements AdminObserver {
    public LoanableAccount(){
        loans = new ArrayList<>();
        // TODO: populate loans with previous loans from database
    }

    protected List<Loan> loans; // loans

    // adds a new loan to the accont
    public void takeNewLoan(double amount, String collateral){
        loans.add(new Loan(amount, collateral));
        // TODO: add this loan to the database
    }

    // get loans in string format
    public List<String> getLoans() {
        List<String> lns = new ArrayList<>();
        for (Loan loan : loans) {
            lns.add(loan.toString());
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
        loans.get(loanId).decreaseAmount(amount);
        // TODO: update the database with right loans
        decreaseBalance(Constants.instance.usdSymbol, amount);
        addToTransactions(new Transaction("account", "loan "+loanId, amount, Clock.instance.getTime()));
    }

    // pay interest from balance to admin or else mark loan as defaulted
    protected void payInterestFromBalance(){
        int loanId = 0;
        for (Loan loan : loans) {
            double years = Clock.instance.getNumOfYearsInDecimal(loan.getStartTime(), Clock.instance.getTime());
            double interest = Constants.instance.interestRate*years*loan.getAmount();

            // decrease that amount from balance and add to defaulted payments if interest is more
            if (getBalance(Constants.instance.usdSymbol) >= interest) {
                decreaseBalance(Constants.instance.usdSymbol, interest);
                addToTransactions(new Transaction("account", "interest to loan "+loanId, interest, Clock.instance.getTime()));
            }
            else{
                double curbalance = getBalance(Constants.instance.usdSymbol);
                decreaseBalance(Constants.instance.usdSymbol, curbalance);
                addToTransactions(new Transaction("account", "interest to loan "+loanId, curbalance, Clock.instance.getTime()));
                loan.addToDefaultedPayments(interest-curbalance);
                // TODO: update the database with right loans
            }

            loanId += 1;
        }
    }

    public void getUpdateFromAdmin() {
        payInterestFromBalance();
    }
}
