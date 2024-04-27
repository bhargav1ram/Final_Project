/*
 * Class to implement functionality for savings.
 * Taking a loan, transfering funds to trading account etc
 */
public class SavingsAccount extends LoanableAccount {
    public SavingsAccount(String uid){
        super(uid);
    }

    

    public void getUpdateFromAdmin() {
        // add interest deposits if the balance is > 3000
        if (getBalance(Constants.get.usdSymbol) >= Constants.get.minimumBalanceForInterests) {
            double years = Clock.get.getNumOfYearsInDecimal(accountOpenTime, Clock.get.getTime());
            double interest = Constants.get.interestRate*years*getBalance(Constants.get.usdSymbol);

            addBalance(Constants.get.usdSymbol, interest);
            addToTransactions(new Transaction("interest deposit", "account", interest, Clock.get.getTime()));
        }
        
        super.getUpdateFromAdmin(); // update the interest for loans
    }
}
