/*
 * Class represents one security of stock of the market. Has database functionality
 */
public class Security {
    private String symbol; // represents the symbol of the stock
    private double price; // price of stock
    private String name; // name of the company

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
