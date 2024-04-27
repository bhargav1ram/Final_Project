/*
 * Class to maintain and put data to database of checkings account
 */
public class CheckingsAccount extends LoanableAccount {
    public CheckingsAccount(String uid, String accId, double openingBalance, String accType){
        super(uid, accId, openingBalance, accType);
        minBalance = Constants.get.minCheckingsBalance;
    }

    public CheckingsAccount(String uid, String accId, String accType){
        super(uid, accId, accType);
        minBalance = Constants.get.minCheckingsBalance;
    }

    public void getUpdateFromAdmin() {
        super.getUpdateFromAdmin(); // update the interest for loans
    }
}
