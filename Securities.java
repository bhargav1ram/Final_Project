/*
 * class to maintain all securites
 */

import java.util.ArrayList;
import java.util.List;

public class Securities implements DatabaseObserver {
    private List<Security> securities; // list of all securities

    public Securities(){
        securities = new ArrayList<Security>();
    }

    // adds new security to list
    private void addSecurity(Security security){
        securities.add(security);
    }

    // gets security with a symbol
    private Security getSecurity(String symbol){
        for (Security security : securities) {
            if (security.getSymbol().equals(symbol)) {
                return security;
            }
        }
        return new Security();
    }

    // update security price
    private void updatePrice(String symbol, double price){
        Security security = getSecurity(symbol);
        security.setPrice(price);
    }

    public void getLatestFromDB() {
        // TODO: add if new securities are added or change the prices of current securities
    }
    
}
