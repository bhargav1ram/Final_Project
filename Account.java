/*
 * Base class for account which will get inherited by multiple types of accounts
 */
import java.util.*;

public class Account {
    protected String userId; // user of the account
    protected List<Transaction> transactions; // list of transactions
    protected List<Cash> balances; // balances of each currency
    protected String accountId; // id of the account
    protected String accountOpenTime; // time of opening account

    public Account(String uid){
        transactions = new ArrayList<>();
        balances = Arrays.asList(new Cash(Constants.get.usdSymbol, 0));
        accountOpenTime = Clock.get.getTime();

        userId = uid;
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

    // withdraw from ATM
    public void withdraw(String symbol, double amount){
        decreaseBalance(symbol, amount);
        addToTransactions(new Transaction("account", "withdrawal", amount, Clock.get.getTime()));
    }

    // deposit from atm
    public void deposit(String symbol, double amount){
        addBalance(symbol, amount);
        addToTransactions(new Transaction("deposit", "account", amount, Clock.get.getTime()));
    }

    // convert from currency to another currency
    public void convert(String fromSymbol, String toSymbol, double amount){
        addBalance(toSymbol, amount);
        decreaseBalance(fromSymbol, amount);
        addToTransactions(new Transaction(fromSymbol, toSymbol, amount, Clock.get.getTime()));
    }
}
