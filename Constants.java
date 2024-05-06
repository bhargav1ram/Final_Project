/*
 * Class to maintain constants
 */
public class Constants {
    private Constants(){} // private constructor
    public final static Constants get = new Constants(); // instance variable

    public final String bankName = "Stratton Oakmont"; // to save name of the bank

    public final String usdSymbol = "USD"; // usd symbol
    public final String euroSymbol = "EUR";
    public final String rupSymbol = "INR";


    public final String defaultTime = "2024-01-01"; // default time for the clock start

    public final double interestRate = 2.0; // APR for the loans or interests
    public final double minimumBalanceForInterests = 3000; // savings with balance > 3000 will be paid interest
    public final double feePercent = 0.5; // percentage of transaction charged as fee

    public final double minSavingsBalance = 2500; // minimum balance for savings account

    public final double accountOpeningAndClosingFee = 50;
    public final double minSavingsAccountOpenBalance = minSavingsBalance/(1.0-feePercent); // minmum balance with which savings account can be opened

    public final double minSavingsForTradingAccount = 5000;
    public final double minCheckingsBalance = 500; // minimum balance for checkings account
    public final double minCheckingsAccountOpenBalance = minCheckingsBalance/(1.0-feePercent); // minmum balance with which checkings account can be opened

    public final double minTradingAccountOpenBalance = 1000;

    // account types
    public final String savingsType = "savings";
    public final String checkingsType = "checkings";
    public final String tradingType = "trading";

    public final String bankuser = "Bank User";

    public final String bankmanager = "Bank Manager";
}
