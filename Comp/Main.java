public class Main {
    public static void main(String[] args) {
        // TradingAccount acc = new TradingAccount("new", "newtrading", 50000.0, "savings");
        // acc.buyShares("APPL", 4);
        // acc.sellShares("APPL", 4);
        // acc.getSharesOfSymbol("APPL").getRealisedProfit();

        Stocks.get.updatePrice("APPL", 20);
        Stocks.get.addStock("POO", "Booeing", 3);
    }
}
