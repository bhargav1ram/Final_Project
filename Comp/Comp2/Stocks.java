/*
 * class to maintain all stocks. has database functionality
 * Singleton class
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Stocks implements AdminObserver {
    private List<Stock> stocks; // list of all stocks
    public static final Stocks get = new Stocks(); // singleton instance

    private Stocks(){
        stocks = new ArrayList<Stock>();
        // TODO: get data from database and update stocks accordingly??(Update how)//View all stocks
        String sql = "SELECT * FROM Stocks";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("CompanyName");
                String symbol = rs.getString("StockSymbol");
                Double price = rs.getDouble("CurrentPrice");

                stocks.add(new Stock(symbol, price, name));
            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // adds new stock to list
    public void addStock(Stock stock){
        stocks.add(stock);

        String sql = "INSERT INTO Stocks (CompanyName, StockSymbol, CurrentPrice) Values (?, ?, ?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, stock.getName());
            pstmt.setString(2, stock.getSymbol());
            pstmt.setDouble(3, stock.getPrice());

            pstmt.executeQuery();
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    public void addStock(String sym, String nm, double pr){
        addStock(new Stock(sym, pr, nm));
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
    public void updatePrice(String symbol, double price){
        Stock stock = getStock(symbol);
        stock.setPrice(price);

        String sql = "UPDATE Stocks SET CurrentPrice = ? WHERE StockSymbol = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, stock.getPrice());
            pstmt.setString(2, stock.getSymbol());

            pstmt.executeQuery();
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
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
