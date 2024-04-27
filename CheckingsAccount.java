/*
 * Class to maintain and put data to database of checkings account
 */
public class CheckingsAccount extends LoanableAccount {
    public CheckingsAccount(String uid){
        super(uid);
    }

    public void getUpdateFromAdmin() {
        super.getUpdateFromAdmin(); // update the interest for loans
    }
}
