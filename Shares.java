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

    public Shares(Stock st){
        stock = st;
        buyPrices = new ArrayList<>();
        sellPrices = new ArrayList<>();
        buyNumOfShares = new ArrayList<>();
        sellNumOfShares = new ArrayList<>();
    }

    // function to buy n shares
    public void buyShares(double numShares){
        numShares += numShares;
        buyPrices.add(stock.getPrice());
        buyNumOfShares.add(numShares);
    }

    // function to sell n shares
    public void sellShares(double numShares){
        if (currentNumOfShares >= numShares) {
            currentNumOfShares-=numShares;
            sellPrices.add(stock.getPrice());
            sellNumOfShares.add(numShares);
        }
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

    // number of shares bought till now
    private double numSharesBought(){
        double num = 0;
        for (Double buynum : buyNumOfShares) {
            num+=buynum;
        }
        return num;
    }

    // number of shares sold till now
    private double numSharesSold(){
        double num = 0;
        for (Double sellnum : sellNumOfShares) {
            num+=sellnum;
        }
        return num;
    }
}
