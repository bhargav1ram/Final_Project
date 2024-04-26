/*
 * Class to maintain how much cash does account have of the given currency
 */


public class Cash {
    private double amount; // amount of currencey of current cash
    private Currency currency; // the type of currency

    public Cash(String symbol, double am){
        currency = Currencies.instance.getCurrency(symbol);
        amount = am;
    }

    public Currency getCurrency(){
        return currency;
    }

    public double getUSDVal(){
        return amount*currency.getExchangeRate();
    }

    public void addCash(double am){
        amount += am;
    }

    public void decreaseCash(double am){
        amount -= am;
    }

    public double getAmount(){
        return amount;
    }

    /*
     * Adds cash equivalient to USD
     */
    public void addCashInUSD(double usdAmount){
        amount += usdAmount/currency.getExchangeRate();
    }

    /*
     * Remove cash equivalient to USD
     */
    public void decreaseCashInUSD(double usdAmount){
        amount -= usdAmount/currency.getExchangeRate();
    }
}
