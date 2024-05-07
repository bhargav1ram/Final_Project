/*
 * class to maintain all curriencies. has database functionality
 */

import java.util.ArrayList;
import java.util.List;

public class Currencies implements AdminObserver {
    private List<Currency> currencies; // list of all currencies
    public static final Currencies get = new Currencies(); // singleton instance

    private Currencies(){
        currencies = new ArrayList<Currency>();
        // TODO: get data from database and update curriences accordingly
    }

    // adds new currency to list
    private void addCurrency(Currency currency){
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

    public void getUpdateFromAdmin() {
        // TODO: add if new currencies are added or change the ERs of current currencies
    }
    
}
