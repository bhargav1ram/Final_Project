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

    protected String sql;

    public Account(String uid, String accId, double openingBalance, String accType){
        minBalance = 0.0;
        transactions = new ArrayList<>();
        balances = Arrays.asList(new Cash(Constants.get.usdSymbol, openingBalance));
        accountOpenTime = Clock.get.getTime();
        userId = uid;
        accountId = accId;
        accountType = accType;
        double empty =0.0;
         // TODO: push new account to the database(Done)//Creating the account
        if(accountType!=Constants.get.tradingType) {
            sql = "INSERT INTO BankAccounts (AccountID, UserID, AccountType, USDBalance, INRBalance, EURBalance, DayOpened) VALUES (?, ?, ?, ?, ?, ?, ?);";
        }else{
            sql = "INSERT INTO TradingAccounts (TradingAccountID, UserID, AccountType, Balance, DayOpened) VALUES (?, ?, ?, ?, ?, ?, ?);";
        }


        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the values received from the method parameters
            if(accountType!=Constants.get.tradingType) {
                pstmt.setString(1, accountId);
                pstmt.setString(2, userId);
                pstmt.setString(3, accountType);
                pstmt.setDouble(4, openingBalance);
                pstmt.setDouble(5, empty);
                pstmt.setDouble(6, empty);
                pstmt.setDate(7, java.sql.Date.valueOf(accountOpenTime));
            }else{
                pstmt.setString(1, accountId);
                pstmt.setString(2, userId);
                pstmt.setString(3, Constants.get.usdSymbol);
                pstmt.setDouble(4, openingBalance);
                pstmt.setDate(5, java.sql.Date.valueOf(accountOpenTime));
            }

            int affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        this.deposit(Constants.get.usdSymbol, openingBalance);
    }

    public Account(String uid, String accId, String accType){
        minBalance = 0.0;
        userId = uid;
        accountId = accId;
        accountType = accType;
        balances = Arrays.asList(new Cash(Constants.get.usdSymbol, 0.0));
        transactions = new ArrayList<>();
        // TODO: get transactions and balances data from database(Why have you used this?)//Pull all data members given in class above

        sql = "SELECT * FROM BankAccounts WHERE UserID = ? AND AccountID = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, accountId);


            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if(accountType!=Constants.get.tradingType) {
                    String accountID = rs.getString("AccountID");
                    String userID = rs.getString("UserID");
                    String accountType = rs.getString("AccountType");
                    Double usdBalance = rs.getDouble("USDBalance");
                    Double inrBalance = rs.getDouble("INRBalance");
                    Double eurBalance = rs.getDouble("EURBalance");
                    balances = Arrays.asList(new Cash(Constants.get.usdSymbol, usdBalance),
                                            new Cash(Constants.get.euroSymbol, eurBalance),
                                            new Cash(Constants.get.rupSymbol, inrBalance));
                    accountOpenTime = String.valueOf(rs.getDate("DayOpened").toLocalDate());
                }else{
                    String accountID = rs.getString("TradingAccountID");
                    String userID = rs.getString("UserID");
                    String symbol = rs.getString("Currency");
                    Double Balance = rs.getDouble("Balance");
                    balances = Arrays.asList(new Cash(Constants.get.usdSymbol, Balance));
                    accountOpenTime = String.valueOf(rs.getDate("DayOpened").toLocalDate());
                }
                Thread.sleep(100);
                sql = "SELECT * FROM Transactions WHERE AccountID = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql);
                pstmt2.setString(1, accountId);
                ResultSet rs2 = pstmt2.executeQuery();
                while (rs2.next()) {
                    transactions.add(new Transaction(rs2.getString("FROM_"), 
                                                    rs2.getString("TO_"), 
                                                    rs2.getDouble("Amount"), 
                                                    String.valueOf(rs2.getDate("TransactionDate").toLocalDate())));
                }
                transactions.sort(Comparator.comparing(Transaction::getTime));
                Thread.sleep(100);
                System.out.println("Data loaded successfully for Account ID: " + accountId);
            } else {
                System.out.println("No account found with the specified details.");
            }
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
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
        // TODO: update balances to the database (Can only add value, so need to convert or do something to remember)//Save amount as it is in db
        String balanceColumn = symbol.concat("Balance");
        sql = "UPDATE BankAccounts SET " + balanceColumn + " = " + balanceColumn + " + ? WHERE UserID = ? AND AccountID = ?";
        try (Connection conn = Database.getConnection(); // Assuming Database.getConnection() is implemented elsewhere
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, amount);
            pstmt.setString(2, userId);
            pstmt.setString(3, accountId);

            int affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();

        }
    }

    // decrease cash balance of certain currency. ex: INR 10, EUR 300
    protected void decreaseBalance(String symbol, double amount){
        for (Cash balance : balances) {
            if (balance.getCurrency().getSymbol().equals(symbol)) {
                balance.decreaseCash(amount);
            }
        }
        // TODO: update balances to the database(Can only add value, so need to convert or do something to remember)//Save amount as it is in db
        String balanceColumn = symbol.concat("Balance");
        sql = "UPDATE BankAccounts SET " + balanceColumn + " = " + balanceColumn + " - ? WHERE UserID = ? AND AccountID = ?";
        try (Connection conn = Database.getConnection(); // Assuming Database.getConnection() is implemented elsewhere
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, amount);
            pstmt.setString(2, userId);
            pstmt.setString(3, accountId);

            int affectedRows = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();

        }
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
        // TODO: update database with new transactions(This stuff is incomplete right? Some account id or stuff should be there in transaction)
        sql = "INSERT INTO Transactions (AccountID, FROM_, TO_, Amount, TransactionDate) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.getConnection(); // Assuming Database.getConnection() is implemented elsewhere
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountId);
            pstmt.setString(2, transaction.getFrom());
            pstmt.setString(3, transaction.getTo());
            pstmt.setDouble(4, transaction.getValueInUSD());
            pstmt.setDate(5, java.sql.Date.valueOf(transaction.getTime())); // Converting LocalDate to java.sql.Date

            int affectedRows = pstmt.executeUpdate();
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();

        }
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
