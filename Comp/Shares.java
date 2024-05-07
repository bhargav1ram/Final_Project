/*
 * Class to maintain shares of a particular stock
 */

import java.util.ArrayList;
import java.util.List;

public class Shares {
    private double currentNumOfShares; // number of shares. Supports fractional shares
    private Stock stock; // the stock of the the shares
    private List<Double> buyPrices; // the buy prices
    private List<Double> sellPrices; // the sell prices
    private List<Double> buyNumOfShares; // number of shares bought
    private List<Double> sellNumOfShares; // number of shares sold
    private List<String> trades; // times of the buys

    public Shares(String userId, String symbol){
        stock = Stocks.get.getStock(symbol);
        buyPrices = new ArrayList<>();
        sellPrices = new ArrayList<>();
        buyNumOfShares = new ArrayList<>();
        sellNumOfShares = new ArrayList<>();
        trades = new ArrayList<>();
        // TODO: fill in shares info of this user and symbol from the database(When is this called?)(Save it in trading account)
    }

    // function to buy n shares
    public void buyShares(double numShares){
        numShares += numShares;
        buyPrices.add(stock.getPrice());
        buyNumOfShares.add(numShares);
        trades.add("Bought: "+numShares+" shares, "+"Price: "+stock.getPrice()+", Time: "+Clock.get.getTime());
        // TODO: push the current info to the database???(Price can lead to confusion)
    }

    // function to sell n shares
    public void sellShares(double numShares){
        if (currentNumOfShares >= numShares) {
            currentNumOfShares-=numShares;
            sellPrices.add(stock.getPrice());
            sellNumOfShares.add(numShares);
            trades.add("Sold: "+numShares+" shares, "+"Price: "+stock.getPrice()+", Time: "+Clock.get.getTime());
            // TODO: push the current info to the database???(Price can lead to confusion)
        }
    }

    // get current value of shares
    public double getUSDVal(){
        return currentNumOfShares*stock.getPrice();
    }

    // buy in USD amounts
    public void buySharesInUSD(double usdAmount){
        buyShares(usdAmount/stock.getPrice());
    }

    // sell in USD amounts
    public void sellSharesInUSD(double usdAmount){
        sellShares(usdAmount/stock.getPrice());
    }

    // getter
    public double getCurrentNumOfShares(){
        return currentNumOfShares;
    }

    public double getUnrealisedProfit(){ // unrealised profit
        return currentNumOfShares*(stock.getPrice()-avgBuyPrice());
    }

    public double getRealisedProfit(){ // realised profit of bought and sold
        return numSharesSold()*(avgSellPrice()-avgBuyPrice());
    }

    private double avgBuyPrice(){ // average buy price
        if (buyPrices.size()==0) {
            return 0;
        }
        double avg = 0;
        for (int i = 0; i < buyPrices.size(); i++) {
            avg += buyPrices.get(i)*buyNumOfShares.get(i);
        }
        return avg/buyPrices.size();
    }

    private double avgSellPrice(){ // avg sell price
        if (sellPrices.size()==0) {
            return 0;
        }
        double avg = 0;
        for (int i = 0; i < sellPrices.size(); i++) {
            avg += sellPrices.get(i)*sellNumOfShares.get(i);
        }
        return avg/sellPrices.size();
    }

    // number of shares sold till now
    private double numSharesSold(){
        double num = 0;
        for (Double sellnum : sellNumOfShares) {
            num+=sellnum;
        }
        return num;
    }

    // get all trades
    public List<String> getTrades(){
        return trades;
    }

    public Stock getStock(){
        return stock;
    }

    public String toString(){
        return currentNumOfShares+" shares of "+stock.getName()+" ("+stock.getSymbol()+") worth $"+getUSDVal();
    }

}
