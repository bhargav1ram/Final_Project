/*
 * clock class for the admin to change and accounts to use.
 * Singleton class
 */

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Clock {
    private String curtime; // current time in the format yyyy-mm-dd
    public final static Clock instance = new Clock();

    private Clock(){
        curtime = Constants.instance.defaultTime;
        // TODO: or bring time from database
    }

    public void setTime(String time){
        curtime = time;
    }

    public String getTime(){
        return curtime;
    }

    public int getNumOfMonths(String startTime, String endTime){
        // converting strings to time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startTime, formatter);
        LocalDate endDate = LocalDate.parse(endTime, formatter);
        
        // getting number of months between them
        Period period = Period.between(startDate, endDate);
        int months = period.getYears() * 12 + period.getMonths();
        
        return months;
    }

    // get number of years between dates in decimal. Ex: 1 year 6 months = 1.5 years
    public double getNumOfYearsInDecimal(String startTime, String endTime){
        // converting strings to time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startTime, formatter);
        LocalDate endDate = LocalDate.parse(endTime, formatter);
        
        // getting number of months between them
        Period period = Period.between(startDate, endDate);
        double months = period.getYears() * 12 + period.getMonths();
        double years = months/12.0;

        return years;
    }
}
