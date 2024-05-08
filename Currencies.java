/*
 * class to maintain all curriencies. has database functionality
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            if (rs.next()) {
                String currencyName = rs.getString("CurrencyName");
                String currencySymbol = rs.getString("CurrencySymbol");
                Double exchangeRate = rs.getDouble("ExchangeRate");

            }
        } catch (SQLException e) {
            System.out.println("Database error occurred:");
            e.printStackTrace();
        }
    }

    // adds new currency to list
    public void addCurrency(Currency currency){
        currencies.add(currency);
    }

    // gets currency with a symbol
    public Currency getCurrency(String symbol){
        for (Currency currency : currencies) {
            if (currency.getSymbol().equals(symbol)) {
                return currency;
            }
        }
        return new Currency();
    }

    // update currency er or exhange rate
    private void updateExchangeRate(String symbol, double er){
        Currency currency = getCurrency(symbol);
        currency.setExchangeRate(er);
    }

    // gets list of all currencies
    public List<String> getCurrencySymbols(){
        List<String> currencySymbols = new ArrayList<>();
        for (Currency currency : currencies) {
            currencySymbols.add(currency.getSymbol());
        }
        return currencySymbols;
    }
    
}
