/*
 * Class represents one current
 */
public class Currency {
    private String symbol; // represents the symbol of the stock
    private double exchangeRate; // exchangeRate of the currency
    private String name; // name of the currency

    public Currency(){
        
    }

    public Currency(String symbol, double exchangeRate, String name) {
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getExchangeRate() {
        return exchangeRate;
    }
    public void setExchangeRate(double er) {
        this.exchangeRate = er;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
