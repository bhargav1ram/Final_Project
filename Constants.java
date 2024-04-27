/*
 * Class to maintain constants
 */
public class Constants {
    private Constants(){} // private constructor
    public final static Constants get = new Constants(); // instance variable

    public final String bankName = "Stratton Oakmont"; // to save name of the bank

    public final String usdSymbol = "USD"; // usd symbol

    public final String defaultTime = "2024-01-01"; // default time for the clock start

    public final double interestRate = 0.1; // APR for the loans or interests
    public final double minimumBalanceForInterests = 3000; // savings with balance > 3000 will be paid interest
}
