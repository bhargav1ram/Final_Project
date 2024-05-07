/* 
 * Base class for account which will get inherited by multiple types of accounts
 */
import java.sql.*;
import java.util.*;

public class Account {
    protected String userId; // user of the account
    protected List<Transaction> transactions; // list of transactions
    protected List<Cash> balances; // balances of each currency
    protected String accountId; // id of the account
    protected String accountOpenTime; // time of opening account
    protected double minBalance; // minimum USD balance for the account
    protected String accountType; // savings or checkings or tranding

}

    public Account(String uid, String accId, double openingBalance, String accType){
        minBalance = 0.0;
        transactions = new ArrayList<>();
        balances = Arrays.asList(new Cash(Constants.get.usdSymbol, 0));
        accountOpenTime = Clock.get.getTime();
        userId = uid;
        accountId = accId;
        accountType = accType;
        // TODO: push new account to the database
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO Accounts (accountId, userId, accountOpenTime, minBalance, accountType) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountId);
            pstmt.setString(2, userId);
            pstmt.setTimestamp(3, Timestamp.valueOf(accountOpenTime));
            pstmt.setDouble(4, minBalance);
            pstmt.setString(5, accountType);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//

        this.deposit(Constants.get.usdSymbol, openingBalance);
    }

    public Account(String uid, String accId, String accType){
        minBalance = 0.0;
        userId = uid;
        accountId = accId;
        accountType = accType;
        // TODO: get transactions and balances data from database
    }

    // add cash of certain currency. ex: INR 10, EUR 300
    protected void addBalance(String symbol, double amount){
        boolean symbolExists = false;
        for (Cash balance : balances) {
            if (balance.getCurrency().getSymbol().equals(symbol)) {
                balance.addCash(amount);
                symbolExists = true;
            }
        }
        if (!symbolExists) {
            balances.add(new Cash(symbol, amount));
        }
        // TODO: update balances to the database
    }

    // decrease cash balance of certain currency. ex: INR 10, EUR 300
    protected void decreaseBalance(String symbol, double amount){
        for (Cash balance : balances) {
            if (balance.getCurrency().getSymbol().equals(symbol)) {
                balance.decreaseCash(amount);
            }
        }
        // TODO: update balances to the database
    }

    // get cash instance of that symbol
    protected Cash getCashOfSymbol(String symbol){
        Cash cash = null;
        for (Cash balance : balances) {
            if (balance.getCurrency().getSymbol().equals(symbol)) {
                cash = balance;
            }
        }
        return cash;
    }

    // get balance of a currency symbol
    public double getBalance(String symbol){
        Cash cash = getCashOfSymbol(symbol);
        if (cash == null) {
            return 0;
        }
        return cash.getAmount();
    }

    // get balance of a currency symbol
    public double getBalanceInUSD(String symbol){
        Cash cash = getCashOfSymbol(symbol);
        if (cash == null) {
            return 0;
        }
        return cash.getAmount()*cash.getCurrency().getExchangeRate();
    }

    public List<String> getCurrencies(){
        List<String> currencies = new ArrayList<>();
        for (Cash balance : balances) {
            currencies.add(balance.getCurrency().getSymbol());
        }
        return currencies;
    }

    public List<String> getTransactions() {
        List<String> trans = new ArrayList<>();
        for (Transaction transaction : transactions) {
            trans.add(transaction.toString());
        }
        return trans;
    }

    // updated transactions in both the class and the database
    protected void addToTransactions(Transaction transaction){
        transactions.add(transaction);
        // TODO: update database with new transactions
    }

    // deposit from atm
    public void deposit(String symbol, double amount){
        if(amount == 0.0) return;
        deductFee(symbol, amount*Constants.get.feePercent);
        amount *= (1.0-Constants.get.feePercent);
        addBalance(symbol, amount);
        addToTransactions(new Transaction("deposit", "account", amount*Currencies.get.getCurrency(symbol).getExchangeRate(), Clock.get.getTime()));
    }

    // function to deduct fees
    protected void deductFee(String symbol, double amount){
        if (amount == 0.0) return;
        decreaseBalance(symbol, amount);
        addToTransactions(new Transaction("account", "fee", amount*Currencies.get.getCurrency(symbol).getExchangeRate(), Clock.get.getTime()));
    }
}