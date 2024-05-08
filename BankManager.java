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

    public void getTodayTransactions(){
        String time =Clock.get.getTime();
        //SQL Query to show all transactions that happened today
        String sql = "SELECT TransactionID, AccountID, FROM_, TO_, Amount, TransactionDate FROM Transactions WHERE TransactionDate = ?";

        try (Connection conn = Database.getConnection(); // Assuming Database.getConnection() is implemented elsewhere
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, java.sql.Date.valueOf(time)); // Set the parameter to today's date

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasTransactions = false;
                while (rs.next()) {
                    int transactionId = rs.getInt("TransactionID");
                    String accountId = rs.getString("AccountID");
                    String from = rs.getString("FROM_");
                    String to = rs.getString("TO_");
                    double amount = rs.getDouble("Amount");
                    String transactionDate = String.valueOf(rs.getDate("TransactionDate").toLocalDate());

                    // Print each transaction
                    System.out.printf("Transaction ID: %d, Account ID: %s, From: %s, To: %s, Amount: %.2f, Date: %s\n",
                            transactionId, accountId, from, to, amount, transactionDate);
                    hasTransactions = true;
                }
                if (!hasTransactions) {
                    System.out.println("No transactions found for today.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }

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

    public void addCurrencies(){
        //Get symbol, name, and exchange rate from manager
        String symbol="";
        String name="";
        double exrate = 0.0;

        Currency add = new Currency();
        add.setSymbol(symbol);
        add.setName(name);
        add.setExchangeRate(exrate);
        Currencies.get.addCurrency(add);
    }
    }