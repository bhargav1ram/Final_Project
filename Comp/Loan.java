/*
 * Class to manage all loans from the admin
 */
public class Loan {
    private double amount; // loan amounts in USD
    private String startTime; // time at which these loans are requested
    private double defaultedPayments; // payment of how much is defaulted
    private String collateral; // collateral for this loan

    public Loan(double amt, String col){
        amount = amt;
        startTime = Clock.get.getTime();
        defaultedPayments = 0;
        collateral = col;
    }

    public Loan(double amt, String st, double defaults, String col){
        amount = amt;
        startTime = st;
        defaultedPayments = defaults;
        collateral = col;
    }

    public double getAmount() {
        return amount;
    }
    public void decreaseAmount(double amt) {
        this.amount -= amt;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public double getDefaultedPayments() {
        return defaultedPayments;
    }
    public String getCollateral() {
        return collateral;
    }
    public void setCollateral(String collateral) {
        this.collateral = collateral;
    }
    
    // adds amount to defaulted payments
    public void addToDefaultedPayments(double amount) {
        defaultedPayments += amount;
    }

    // returns true if this loan interest has not been paid
    public boolean isDefaulted(){
        return defaultedPayments > 0;
    }

    public String toString(){
        return "Amount: "+amount+", Start time: "+startTime+", Collateral: "+collateral+(isDefaulted()?". DEFAULTED":"");
    }
}
