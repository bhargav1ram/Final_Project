import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BankUser extends User {
    BankUser user;
    Random random = new Random();
    private SavingsAccount savingsAccount;
    private CheckingsAccount checkingsAccount;
    private TradingAccount tradingAccount;
    private static List <BankUser> customers = new ArrayList<>();
    public BankUser(String name, String userId, String password, String role) {
        super(name, userId, password, role);
    }

    public static List<BankUser> getUsers(){
        return customers;
    }
    public boolean createNew() {
        //Get username,role and password from frontend
        int randomNumber = 100000 + random.nextInt(900000);
        userId=String.valueOf(randomNumber);
        String sql = "INSERT INTO Users (userId, name, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.userId);
            pstmt.setString(2, this.name);
            pstmt.setString(3, this.password);
            pstmt.setString(4, this.role);

            int result = pstmt.executeUpdate();
            BankUser bankUser = new BankUser(name, userId,password,role);
            customers.add(bankUser);

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    //Open savings bank account
    public boolean createSavingsAccount() {
        if (savingsAccount == null) {
            //Get opening balance from user
            double openingBalance=100000;
            if(openingBalance<Constants.get.minSavingsAccountOpenBalance){
                System.out.println("You need a minimimum of "+Constants.get.minSavingsAccountOpenBalance +" to open a Savings account");
                return false;
            }else{
                int randomNumber = 100000 + random.nextInt(900000);
                String accountId=String.valueOf(randomNumber);
                openingBalance-=Constants.get.accountOpeningAndClosingFee;
                BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
                savingsAccount = new SavingsAccount(userId, accountId,openingBalance ,Constants.get.savingsType);
                return true;
            }
        }
        else{
            System.out.println("You already have one savings account");
            return false;
        }

    }

    //Open checking bank account
    public boolean createCheckingsAccount() {
        if (checkingsAccount == null) {
            //Get opening balance from user
            double openingBalance=100000;
            if(openingBalance<Constants.get.minCheckingsAccountOpenBalance){
                System.out.println("You need a minimimum of "+Constants.get.minCheckingsAccountOpenBalance +" to open a Checking account");
                return false;
            }else{
                int randomNumber = 100000 + random.nextInt(900000);
                String accountId=String.valueOf(randomNumber);
                openingBalance=openingBalance-Constants.get.accountOpeningAndClosingFee;
                BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
                checkingsAccount = new CheckingsAccount(userId, accountId,openingBalance ,Constants.get.checkingsType);
                return true;
            }
        }
        else{
            System.out.println("You already have one checking account");
            return false;
        }

    }

    //Open trading bank account
    public boolean createTradingAccount() {
        double savingsbalance = savingsAccount.getCashOfSymbol(Constants.get.usdSymbol).getAmount();
        if (tradingAccount == null && savingsbalance>=Constants.get.minSavingsForTradingAccount) {//Checking USD Balance of savings account
            //Get opening balance from user
            double openingBalance=100000;

            if(openingBalance<Constants.get.minTradingAccountOpenBalance){
                System.out.println("You need a minimimum of "+Constants.get.minTradingAccountOpenBalance +" to open a Savings account");
                return false;
            }else if(savingsbalance-openingBalance<2500){
                System.out.println("Please maintain minimum of 2500 USD in Savings Account");
                return false;
            }else{
                int randomNumber = 100000 + random.nextInt(900000);
                String accountId=String.valueOf(randomNumber);
                savingsAccount.balances.get(0).decreaseCash(openingBalance);
                openingBalance=openingBalance-Constants.get.accountOpeningAndClosingFee;
                BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
                tradingAccount = new TradingAccount(userId, accountId,openingBalance ,Constants.get.tradingType);
                return true;
            }
        }
        else{
            System.out.println("You already have one checking account");
            return false;
        }

    }
    public void addMoneyInSavings(){
        String currency="";//Add currency symbol
        double amount = 10;//Add euro amount
        savingsAccount.addBalance(currency,amount);

    }
    public void addMoneyInCheckings(){
        String currency="";//Add currency symbol
        double amount = 10;//Add euro amount
        checkingsAccount.addBalance(currency,amount);

    }


    public void removeMoneyFromSavings(){
        String currency= ""; //Add currency symbol
        double amount = 10;//Add euro amount
        if(amount<savingsAccount.getWithdrawableBalance(currency)) {
            savingsAccount.decreaseBalance(currency, amount);
        }else{
            //Cant withdraw more than balance
        }
    }

    public void removeMoneyFromCheckings(){
        String currency= ""; //Add currency symbol
        double amount = 10;//Add euro amount
        if(amount<checkingsAccount.getWithdrawableBalance(currency)) {
            double transactionFee = amount * (Constants.get.feePercent);
            BankManager.getBanksavingsAccount().addBalance(currency, transactionFee);
            checkingsAccount.withdraw(currency, amount);
        }
    }
    public boolean getLoan(){
        //Ask user if they want loan from Checking or Savings Account
        String accounttype = "";

        if (accounttype.equals(Constants.get.savingsType)){
            if(savingsAccount== null || savingsAccount.hasDefaultedLoan()){//If no savings account or if user has defaulted loan no savings account
                return false;//Not allowed to take loan if user has defaulted
            }else {//Ask user for the amount of loan they want and the collateral they are putting
                double amount = 100.0;
                String collateral = "";
                savingsAccount.takeNewLoan(amount, collateral);
                return true;
            }
        }else if(accounttype.equals(Constants.get.checkingsType)){
            if(checkingsAccount== null|| checkingsAccount.hasDefaultedLoan()){
                return false;
            }else {//Ask user for the amount of loan they want and the collateral they are putting
                double amount = 100.0;
                String collateral = "";
                checkingsAccount.takeNewLoan(amount, collateral);
                return true;
            }
        }
        return false;
    }

    public List<String> viewSavingsLoans(){
        return savingsAccount.getLoansDesc();
    }

    public List<String> viewCheckingsLoans(){
        return checkingsAccount.getLoansDesc();
    }

    public void paySavingsLoans(){
        //Ask user for loan id for loan they want to pay and amount they want to pay off
        int id= 1;
        double amount = 100;
        savingsAccount.payOffLoan(id,amount);
    }
    public void payCheckingLoans(){
        //Ask user for loan id for loan they want to pay and amount they want to pay off
        int id= 1;
        double amount = 100;
        checkingsAccount.payOffLoan(id,amount);
    }

    public void viewSavingsTransactions(){
        savingsAccount.getTransactions();
    }

    public void viewCheckingsTransactions(){
        checkingsAccount.getTransactions();
    }

    public double viewSavingsBalance(){
        //Get symbol from user for which currency balance (INR,USD,EUR)
        String symbol="";
        return savingsAccount.getBalance(symbol);
    }

    public double viewCheckingsBalance(){
        String symbol="";
        return checkingsAccount.getBalance(symbol);
    }

    public void closeSavingsAccount(){
        savingsAccount.decreaseBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        savingsAccount=null;
    }

    public void closeCheckingsAccount(){
        checkingsAccount.decreaseBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        checkingsAccount=null;
    }

    public void closeTradingAccount(){
        savingsAccount.decreaseBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, Constants.get.accountOpeningAndClosingFee);
        tradingAccount=null;
    }

    public void performSavingsAccountTransactions(){
        //Get who the money is being sent to, and what amount is being sent.
        String to = "";
        double value = 0.0;
        if(value<savingsAccount.getWithdrawableBalance(Constants.get.usdSymbol)) {
            savingsAccount.decreaseBalance(Constants.get.usdSymbol, value);
            Transaction transaction = new Transaction(name, to, value, Clock.get.getTime());
        }
    }

    public void performCheckingsAccountTransactions(){
        //Get who the money is being sent to, and what amount is being sent.
        String to = "";
        double value = 0.0;
        if(value<checkingsAccount.getWithdrawableBalance(Constants.get.usdSymbol)) {
            double transactionFee = value * (Constants.get.feePercent / 100);
            checkingsAccount.decreaseBalance(Constants.get.usdSymbol, value + transactionFee);
            BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, transactionFee);
            Transaction transaction = new Transaction(name, to, value, Clock.get.getTime());
        }
    }

    public void buySharesViaSharesCount(){
        //enter symbol and number of shares
        String sym="";
        int noOfShares= 10;
        tradingAccount.buyShares(sym,noOfShares);
    }

    public void buySharesViaUSDAmount(){
        //enter symbol and number of shares
        String sym="";
        double amount= 10;
        tradingAccount.buySharesInUSD(sym,amount);
    }

    public void sellSharesviaSharesCount(){
        //enter symbol and number of shares
        String sym="";
        int noOfShares= 10;
        tradingAccount.sellShares(sym,noOfShares);
    }

    public void realisedProfit(){
        String sym="";
        if(tradingAccount.getSharesOfSymbol(sym)!=null)
            tradingAccount.getSharesOfSymbol(sym).getRealisedProfit();
    }

    public void unrealisedProfit(){
        String sym="";
        if(tradingAccount.getSharesOfSymbol(sym)!=null)
            tradingAccount.getSharesOfSymbol(sym).getUnrealisedProfit();
    }

    public void getOpenPositions(){
        tradingAccount.getStockSymbols();
    }

    public boolean hasSavings(){
        if(savingsAccount!=null){
            return true;
        }else
            return false;
    }

    public boolean hasCheckings(){
        if(checkingsAccount!=null){
            return true;
        }else
            return false;
    }

    public SavingsAccount getSavings(){
        return savingsAccount;
    }

    public CheckingsAccount getCheckings(){
        return checkingsAccount;
    }

}