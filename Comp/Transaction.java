/*
 * class to manage a particular transaction
 */
public class Transaction {
    private String from; // from where to money is being deducted
    private String to; // to where the money is being deposited
    private double valueInUSD; // value of the transaction in USD
    private String time; // time of transaction in the format yyyy-mm-dd

    public Transaction(String from, String to, double valueInUSD, String time){
        this.from = from;
        this.to = to;
        this.valueInUSD = valueInUSD;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public double getValueInUSD() {
        return valueInUSD;
    }
    public void setValueInUSD(double valueInUSD) {
        this.valueInUSD = valueInUSD;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String toString(){
        return "From: "+from+", To: "+to+", Value: "+valueInUSD+", Time: "+time;
    }
    
}
