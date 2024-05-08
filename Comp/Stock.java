/*
 * Class represents one stock of stock market
 */
public class Stock {
    private String symbol; // represents the symbol of the stock
    private double price; // price of stock
    private String name; // name of the company

    public Stock() {
    }
    
    public Stock(String symbol, double price, String name) {
        this.symbol = symbol;
        this.price = price;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
