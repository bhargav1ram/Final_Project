/*
 * Class which supports all functionality of tranding account
 * which has a balance and has functionality to buy and sell stocks
 */

import java.util.*;

public class TradingAccount extends Account {
    private List<Shares> sharesTotal; // variable to store all the shares

    public TradingAccount(String uid, String accId, double zeroOpeningBalance, String accType){
        super(uid, accId, 0.0, accType);
        sharesTotal = new ArrayList<>();
        // TODO: update shares info in database???(when is this used?)//On creation of trading account
    }

    public TradingAccount(String uid, String accId, String accType){
        super(uid, accId, accType);
        // TODO: populate shares with previous shares from database(when is this used?)//Get info of trading account from db
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
        }
        newShares.buyShares(numOfShares);
        // TODO: save shares to account here or in the shares class??

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
        if (curShares.getCurrentNumOfShares() == 0) {
            sharesTotal.remove(curShares);
        }
        // TODO: save shares to account here or in the shares class??

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
    private Shares getSharesOfSymbol(String symbol){
        Shares shares = null;
        for (Shares curshares : sharesTotal) {
            if (curshares.getStock().getSymbol() == symbol) {
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
