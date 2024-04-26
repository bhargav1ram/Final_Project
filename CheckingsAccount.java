/*
 * Class to maintain and put data to database of checkings account
 */
public class CheckingsAccount extends LoanableAccount {
    public CheckingsAccount(String uid){
        userId = uid;
        // TODO: get transactions and balances data from database
    }

    public void getUpdateFromAdmin() {
        super.getUpdateFromAdmin(); // update the interest for loans
    }
}
