/*
 * Class which supports all functionality of tranding account
 * which has a balance and has functionality to buy and sell stocks
 */

import java.util.*;
import java.sql.*;

public class TradingAccount extends Account {
    private List<Shares> sharesTotal; // variable to store all the shares

    public TradingAccount(String uid, String accId, double zeroOpeningBalance, String accType){
        super(uid, accId, zeroOpeningBalance, accType);
        sharesTotal = new ArrayList<>();
        // TODO: update shares info in database???(when is this used?)//On creation of trading account
    }

    public TradingAccount(String uid, String accId, String accType){
        super(uid, accId, accType);
        sharesTotal = new ArrayList<>();
        // TODO: populate shares with previous shares from database(when is this used?)//Get info of trading account from db
        String sql = "SELECT StockSymbol, currentNumOfShares, buyPrices, sellPrices, buyNumOfShares, sellNumOfShares, trades FROM StockHoldings WHERE TradingAccountID = ?";

        try (Connection conn = Database.getConnection(); // Using the provided Database class for connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountId);  // Set the account ID parameter

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String symbol = rs.getString("StockSymbol");                    
                    double currentNumOfShares = rs.getDouble("currentNumOfShares");
                    List<Double> buyPrices = stringToDoubles(rs.getString("buyPrices"));
                    List<Double> sellPrices = stringToDoubles(rs.getString("sellPrices"));
                    List<Double> buyNumOfShares = stringToDoubles(rs.getString("buyNumOfShares"));
                    List<Double> sellNumOfShares = stringToDoubles(rs.getString("sellNumOfShares"));
                    List<String> trades = stringToTrades(rs.getString("trades"));

                    Shares curShares = new Shares("", symbol);
                    curShares.setCurrentNumOfShares(currentNumOfShares);
                    curShares.setBuyPrices(buyPrices);
                    curShares.setSellPrices(sellPrices);
                    curShares.setBuyNumOfShares(buyNumOfShares);
                    curShares.setSellNumOfShares(sellNumOfShares);
                    curShares.setTrades(trades);

                    sharesTotal.add(curShares);
                }

            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    private void updateSharesToDb(String symbol){
        String sql = "UPDATE StockHoldings SET currentNumOfShares = ?, buyPrices = ?, sellPrices = ?, buyNumOfShares = ?, sellNumOfShares = ?, trades = ? WHERE TradingAccountID = ? AND StockSymbol = ?";

        try (Connection conn = Database.getConnection(); // Using the provided Database class for connection
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            Shares curShares = getSharesOfSymbol(symbol);
            pstmt.setDouble(1, curShares.getCurrentNumOfShares());
            pstmt.setString(2, listToString(curShares.getBuyPrices()));
            pstmt.setString(3, listToString(curShares.getSellPrices()));
            pstmt.setString(4, listToString(curShares.getBuyNumOfShares()));
            pstmt.setString(5, listToString(curShares.getSellNumOfShares()));
            pstmt.setString(6, listToString(curShares.getTrades()));
            pstmt.setString(7, accountId);
            pstmt.setString(8, symbol);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("shares successfully added to the database.");
            } else {
                System.out.println("Failed to add the shares to the database.");
            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    private void insertSharesToDb(String symbol){
        String sql = "INSERT INTO StockHoldings (TradingAccountID, StockSymbol, currentNumOfShares, buyPrices, sellPrices, buyNumOfShares, sellNumOfShares, trades) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection(); // Using the provided Database class for connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            Shares curShares = getSharesOfSymbol(symbol);
            
            pstmt.setString(1, accountId);
            pstmt.setString(2, symbol);
            pstmt.setDouble(3, curShares.getCurrentNumOfShares());
            pstmt.setString(4, listToString(curShares.getBuyPrices()));
            pstmt.setString(5, listToString(curShares.getSellPrices()));
            pstmt.setString(6, listToString(curShares.getBuyNumOfShares()));
            pstmt.setString(7, listToString(curShares.getSellNumOfShares()));
            pstmt.setString(8, listToString(curShares.getTrades()));

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("shares successfully added to the database.");
            } else {
                System.out.println("Failed to add the shares to the database.");
            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    private String listToString(List<?> objs){
        if (objs.size() == 0) {
            return "";
        }
        String retval = "";
        List<String> newlist = new ArrayList<>();
        for (Object obj : objs) {
            newlist.add(obj.toString());
        }
        retval = String.join(";", newlist);
        return retval;
    }

    private List<Double> stringToDoubles(String str){
        List<String> strs = stringToTrades(str);
        List<Double> retval = new ArrayList<>();
        for (String strdouble : strs) {
            try{
                retval.add(Double.parseDouble(strdouble));
            }catch(Exception e){}
        }
        return retval;
    }

    private List<String> stringToTrades(String str){
        if (str.equals("")) {
            return new ArrayList<String>();
        }
        return Arrays.asList(str.split(";"));
    }

    // maximum shares one can buy of a symbol due to balance restrictions
    public double maxSharesCanBuy(String symbol){
        return getBalance(Constants.get.usdSymbol)/Stocks.get.getStock(symbol).getPrice();
    }

    // maximum shares In USD amounts one can buy of a symbol due to balance restrictions
    public double maxSharesCanBuyInUSD(String symbol){
        return getBalance(Constants.get.usdSymbol);
    }

    // function to buy shares in number of shares of a symbol
    public void buyShares(String symbol, double numOfShares){
        // adding shares
        Shares newShares = getSharesOfSymbol(symbol);
        if (newShares == null) {
            newShares = new Shares(userId, symbol);
            sharesTotal.add(newShares);
            insertSharesToDb(symbol);
        }
        newShares.buyShares(numOfShares);
        // TODO: save shares to account here or in the shares class??
        updateSharesToDb(symbol);
        // logging transactions and decreasing balance
        double amount = numOfShares*Stocks.get.getStock(symbol).getPrice();
        decreaseBalance(Constants.get.usdSymbol, amount);
        addToTransactions(new Transaction("account", "bought "+numOfShares+" shares of "+symbol, amount, Clock.get.getTime()));
    }

    // use this to buy shares in USD amounts
    public void buySharesInUSD(String symbol, double amount){
        buyShares(symbol, amount/Stocks.get.getStock(symbol).getPrice());
    }

    // maximum shares one can sell for a symbol
    public double maxSharesCanSell(String symbol){
        Shares curShares = getSharesOfSymbol(symbol);
        if (curShares == null) {
            return 0.0;
        }
        return curShares.getCurrentNumOfShares();
    }

    // maximum shares one can sell in USD for a symbol
    public double maxSharesCanSellInUSD(String symbol){
        return maxSharesCanSell(symbol)*Stocks.get.getStock(symbol).getPrice();
    }

    // buy n number of shares of a symbol
    public void sellShares(String symbol, double numOfShares){
        Shares curShares = getSharesOfSymbol(symbol);
        curShares.sellShares(numOfShares);
        // TODO: save shares to account here or in the shares class??
        updateSharesToDb(symbol);
        // logging and balance changes
        double amount = numOfShares*Stocks.get.getStock(symbol).getPrice();
        addBalance(Constants.get.usdSymbol, amount);
        addToTransactions(new Transaction("sold "+numOfShares+" shares of "+symbol, "account", amount, Clock.get.getTime()));
    }

    // buy shares of a symbol of amount x in USD
    public void sellSharesInUSD(String symbol, double amount){
        sellShares(symbol, amount/Stocks.get.getStock(symbol).getPrice());
    }

    // doesn't automatically reduce savings balance
    public void depositFromSavings(double amount){
        addBalance(Constants.get.usdSymbol, amount);
        addToTransactions(new Transaction("savings account", "account", amount, Clock.get.getTime()));
    }

    // doesn't automatically increase savings balance
    public void withdrawIntoSavings(double amount){
        decreaseBalance(Constants.get.usdSymbol, amount);
        addToTransactions(new Transaction("account", "savings account", amount, Clock.get.getTime()));
    }

    // get shares of a particular symbol like get shares of APPL or MRST (apple or microsoft)
    public Shares getSharesOfSymbol(String symbol){
        Shares shares = null;
        for (Shares curshares : sharesTotal) {
            if (curshares.getStock().getSymbol().equals(symbol)) {
                shares = curshares;
            }
        }
        return shares;
    }

    // gets all the symbols that the user owns
    public List<String> getStockSymbols(){
        List<String> sym = new ArrayList<>();
        for (Shares share : sharesTotal) {
            sym.add(share.getStock().getSymbol());
        }
        return sym;
    }

    // gets shares description
    public List<String> getSharesDesc(){
        List<String> desc = new ArrayList<>();
        for (Shares share : sharesTotal) {
            desc.add(share.toString());
        }
        return desc;
    }
}
