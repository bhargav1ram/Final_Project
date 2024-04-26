/*
 * Class to implement functionality for savings.
 * Taking a loan, transfering funds to trading account etc
 */
public class SavingsAccount extends LoanableAccount {
    public SavingsAccount(String uid){
        userId = uid;
        // TODO: get transactions and balances data from database
    }

    public void getUpdateFromAdmin() {
        // add interest deposits if the balance is > 3000
        if (getBalance(Constants.instance.usdSymbol) >= Constants.instance.minimumBalanceForInterests) {
            double years = Clock.instance.getNumOfYearsInDecimal(accountOpenTime, Clock.instance.getTime());
            double interest = Constants.instance.interestRate*years*getBalance(Constants.instance.usdSymbol);

            addBalance(Constants.instance.usdSymbol, interest);
            addToTransactions(new Transaction("interest deposit", "account", interest, Clock.instance.getTime()));
        }
        
        super.getUpdateFromAdmin(); // update the interest for loans
    }
}
