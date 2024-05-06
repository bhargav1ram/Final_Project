import java.sql.*;
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

    public void getTodayTransactions(){
        String time =Clock.get.getTime();
        //SQL Query to show all transactions that happened today

    }

    public void payInterest(){
        //Sql query to check accounts which have higher balance than minimumBalanceForInterests they are given interest at interestRate
        double Interest = Constants.get.interestRate;
        double MinimumBalance = Constants.get.minimumBalanceForInterests;
        int monthspassed = Clock.get.getNumOfMonths(Clock.get.getPrevTime(), Clock.get.getTime());
        if(monthspassed>0) {
            Double formula = (Interest * monthspassed)/(100*12);//Multiply this formula with ever person who has minimum savings balance for interest

        }

    }

    public void changeTime(){
        //Get date from user
        String time="";
        Clock.get.setTime(time);
        payInterest();
    }



}