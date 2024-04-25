/*
 * class to maintain all stocks. has database functionality
 */

import java.util.ArrayList;
import java.util.List;

public class Stocks implements DatabaseObserver {
    private List<Stock> securities; // list of all securities

    public Stocks(){
        securities = new ArrayList<Stock>();
    }

    // adds new stock to list
    private void addStock(Stock stock){
        securities.add(stock);
    }

    // gets stock with a symbol
    private Stock getStock(String symbol){
        for (Stock stock : securities) {
            if (stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        return new Stock();
    }

    // update stock price
    private void updatePrice(String symbol, double price){
        Stock stock = getStock(symbol);
        stock.setPrice(price);
    }

    // gets list of all securities
    public List<String> getStockSymbols(){
        List<String> stockSymbols = new ArrayList<>();
        for (Stock stock : securities) {
            stockSymbols.add(stock.getSymbol());
        }
        return stockSymbols;
    }

    public void getLatestFromDB() {
        // TODO: add if new securities are added or change the prices of current securities
    }
    
}
