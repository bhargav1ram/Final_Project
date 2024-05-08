/*
 * class to maintain all curriencies. has database functionality
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Currencies {
    private List<Currency> currencies; // list of all currencies
    public static final Currencies get = new Currencies(); // singleton instance


    private Currencies(){
        currencies = new ArrayList<Currency>();
        // TODO: get data from database and update curriences accordingly???(Used at startup)(Display exchange rate with usd)
        String sql = "SELECT * FROM BankCurrencies";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String currencyName = rs.getString("CurrencyName");
                String currencySymbol = rs.getString("CurrencySymbol");
                Double exchangeRate = rs.getDouble("ExchangeRate");

                currencies.add(new Currency(currencySymbol, exchangeRate, currencyName));
            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // adds new currency to list
    public void addCurrency(Currency currency){
        currencies.add(currency);

        String sql = "INSERT INTO BankCurrencies (CurrencyName, CurrencySymbol, ExchangeRate) Values (?, ?, ?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, currency.getName());
            pstmt.setString(2, currency.getSymbol());
            pstmt.setDouble(3, currency.getExchangeRate());

            pstmt.executeUpdate();
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // gets currency with a symbol
    public Currency getCurrency(String symbol){
        for (Currency currency : currencies) {
            if (currency.getSymbol().equals(symbol)) {
                return currency;
            }
        }
        return null;
    }

    // update currency er or exhange rate
    public void updateExchangeRate(String symbol, double er){
        Currency currency = getCurrency(symbol);
        currency.setExchangeRate(er);

        String sql = "UPDATE BankCurrencies SET ExchangeRate = ? WHERE CurrencySymbol = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, currency.getExchangeRate());
            pstmt.setString(2, currency.getSymbol());

            pstmt.executeUpdate();
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // gets list of all currencies
    public List<String> getCurrencySymbols(){
        List<String> currencySymbols = new ArrayList<>();
        for (Currency currency : currencies) {
            currencySymbols.add(currency.getSymbol());
        }
        return currencySymbols;
    }

    public void addCurrency(String symbol, String name, double exrate){
        Currency add = new Currency();
        add.setSymbol(symbol);
        add.setName(name);
        add.setExchangeRate(exrate);
        Currencies.get.addCurrency(add);
    }
    
}
