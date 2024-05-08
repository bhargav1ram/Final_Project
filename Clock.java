/*
 * clock class for the admin to change and accounts to use.
 * Singleton class
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Clock {
    private String curtime; // current time in the format yyyy-mm-dd
    private String prevtime;
    public final static Clock get = new Clock();

    private Clock(){
        curtime = Constants.get.defaultTime;
        prevtime = null;
        // TODO: or bring time from database(Add initial value in db manually. Further updates will be added in DB)


    }

    public void setTime(String time){


        if(getIsBefore(prevtime,time)){
            prevtime = getTime();
        }
        curtime = time;
        String query = "SELECT CurrentDate FROM CurrentTime WHERE CurrentDate = (SELECT MAX(CurrentDate) FROM CurrentTime)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                Date lastDate = rs.getDate("CurrentDate");
                System.out.println("Last date from CurrentTime table: " + lastDate);
            } else {
                System.out.println("No dates found in CurrentTime table.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
        }
    }

    public String getTime(){
        return curtime;
    }

    public String getPrevTime(){
        return prevtime;
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

    public boolean getIsBefore(String Date1, String Date2) {
        // converting strings to time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate Date = LocalDate.parse(Date1, formatter);
        LocalDate secondDate = LocalDate.parse(Date2, formatter);

        if(Date.isBefore(secondDate)){
            return true;
        }

        return false;

    }
}