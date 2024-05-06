/*
 * class to maintain all stocks. has database functionality
 * Singleton class
 */

import java.util.ArrayList;
import java.util.List;

public class Stocks implements AdminObserver {
    private List<Stock> stocks; // list of all stocks
    public static final Stocks get = new Stocks(); // singleton instance

    private Stocks(){
        stocks = new ArrayList<Stock>();
        // TODO: get data from database and update stocks accordingly??(Update how)//View all stocks
    }

    // adds new stock to list
    private void addStock(Stock stock){
        stocks.add(stock);
    }

    // gets stock with a symbol
    public Stock getStock(String symbol){
        for (Stock stock : stocks) {
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

    // gets list of all stocks
    public List<String> getStockSymbols(){
        List<String> stockSymbols = new ArrayList<>();
        for (Stock stock : stocks) {
            stockSymbols.add(stock.getSymbol());
        }
        return stockSymbols;
    }

    public void getUpdateFromAdmin() {
        // TODO: add if new stocks are added or change the prices of current stocks??(What is this for)(Have you left this blank for me?)
    }
    
}
