import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;


public class BankUser extends User {
    BankUser user;
    Random random = new Random();
    private SavingsAccount savingsAccount;
    private CheckingsAccount checkingsAccount;
    private TradingAccount tradingAccount;
    public BankUser(String name, String userId, String password, String role) {
        super(name, userId, password, role);
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
        savingsAccount.decreaseBalance(currency,amount);
    }

    public void removeMoneyFromCheckings(){
        String currency= ""; //Add currency symbol
        double amount = 10;//Add euro amount
        double transactionFee= amount *(Constants.get.feePercent/100);
        BankManager.getBanksavingsAccount().addBalance(currency,transactionFee);
        checkingsAccount.decreaseBalance(currency,amount);

    }


    public boolean getLoan(){
        //Ask user if they want loan from Checking or Savings Account
        String accounttype = "";

        if (accounttype.equals(Constants.get.savingsType)){
            if(savingsAccount.hasDefaultedLoan()){
                return false;
            }else {//Ask user for the amount of loan they want and the collateral they are putting
                double amount = 100.0;
                String collateral = "";
                savingsAccount.takeNewLoan(amount, collateral);
                return true;
            }
        }else if(accounttype.equals(Constants.get.checkingsType)){
            if(savingsAccount.hasDefaultedLoan()){
                return false;
            }else {//Ask user for the amount of loan they want and the collateral they are putting
                double amount = 100.0;
                String collateral = "";
                savingsAccount.takeNewLoan(amount, collateral);
                return true;
            }
        }
        return false;
    }

    public void viewSavingsLoans(){
        savingsAccount.getLoansDesc();
    }

    public void viewCheckingsLoans(){
        checkingsAccount.getLoansDesc();
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

    public void viewSavingsBalance(){
        //Get symbol from user for which currency balance (INR,USD,EUR)
        String symbol="";
        savingsAccount.getBalance(symbol);
    }

    public void viewCheckingsBalance(){
        String symbol="";
        checkingsAccount.getBalance(symbol);
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
        savingsAccount.decreaseBalance(Constants.get.usdSymbol, value);
        Transaction transaction = new Transaction(name,to,value,Clock.get.getTime());
    }

    public void performCheckingsAccountTransactions(){
        //Get who the money is being sent to, and what amount is being sent.
        String to = "";
        double value = 0.0;
        double transactionFee= value *(Constants.get.feePercent/100);
        checkingsAccount.decreaseBalance(Constants.get.usdSymbol, value+transactionFee);
        BankManager.getBanksavingsAccount().addBalance(Constants.get.usdSymbol, transactionFee);
        Transaction transaction = new Transaction(name,to,value,Clock.get.getTime());
    }


}