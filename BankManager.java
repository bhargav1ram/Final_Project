import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankManager extends User {

    protected static SavingsAccount banksavingsAccount = new SavingsAccount("M1", "001",100000000 ,Constants.get.savingsType);

    Random random = new Random();
    public BankManager(String name, String userId, String password, String role) {
        super(name, userId, password, role);
    }

    public static SavingsAccount getBanksavingsAccount(){
        return banksavingsAccount;
    }

    public void getTodayTransactions(){//Not complete
        String time =Clock.get.getTime();
        //SQL Query to show all transactions that happened today

    }

    public void payInterest(){//Not complete
        //Sql query to check accounts which have higher balance than minimumBalanceForInterests they are given interest at interestRate
        double Interest = Constants.get.interestRate;
        double MinimumBalance = Constants.get.minimumBalanceForInterests;
        int monthspassed = Clock.get.getNumOfMonths(Clock.get.getPrevTime(), Clock.get.getTime());
        if(monthspassed>0) {
            Double formula = (Interest * monthspassed)/(100*12);//Multiply this formula with ever person who has minimum savings balance for interest

        }

    }
    public void addStocks(){
        Stock stock= new Stock();
        stock.setPrice(12);//Get price, name and symbol of stock from user
        stock.setName("SSF");
        stock.setSymbol("NEO");
        Stocks.get.addStock(stock);
    }
    public void updateStock(){//Get new price and symbol for the stock
        String symbol="";
        double price = 0;
        Stocks.get.updatePrice(symbol,price);
    }
    public void changeTime(){
        //Get date from user
        String time="";
        Clock.get.setTime(time);
        payInterest();
    }

    public void checkUpOnUsersByName(){
        List<BankUser> allCustomers = BankUser.getUsers();
        String searchName="";
        for (int i =0; i<allCustomers.size();i++){
            if(allCustomers.get(i).name.equals(searchName)){
                if(allCustomers.get(i).hasSavings()){
                    allCustomers.get(i).viewSavingsBalance();
                }
            }
        }
    }
    public List<BankUser> checkUpOnPoorUsers(){
        List<BankUser> allCustomers = BankUser.getUsers();
        List<BankUser> poorUsers = new ArrayList<>();
        String searchName="";
        for (int i =0; i<allCustomers.size();i++){
                if(allCustomers.get(i).hasSavings()&& allCustomers.get(i).getSavings().hasDefaultedLoan() || allCustomers.get(i).hasCheckings()&& allCustomers.get(i).getCheckings().hasDefaultedLoan()){
                    poorUsers.add(allCustomers.get(i));
                }
            }
        return  poorUsers;
        }

        public void collectInterestFromLoans(){

        }
    }