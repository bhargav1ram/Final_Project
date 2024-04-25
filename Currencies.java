/*
 * class to maintain all curriencies. has database functionality
 */

import java.util.ArrayList;
import java.util.List;

public class Currencies implements DatabaseObserver {
    private List<Currency> currencies; // list of all currencies

    public Currencies(){
        currencies = new ArrayList<Currency>();
    }

    // adds new currency to list
    private void addCurrency(Currency currency){
        currencies.add(currency);
    }

    // gets currency with a symbol
    private Currency getCurrency(String symbol){
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

    public void getLatestFromDB() {
        // TODO: add if new currencies are added or change the ers of current currencies
    }
    
}
