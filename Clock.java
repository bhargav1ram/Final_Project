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
    public final static Clock get = new Clock();

    private Clock(){
        curtime = Constants.get.defaultTime;
        // TODO: or bring time from database(Add initial value in db manually. Further updates will be added in DB)
        String query = "SELECT CurrentDate FROM CurrentTime";

        try (Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                curtime = rs.getDate("CurrentDate").toString();
                System.out.println("Last date from CurrentTime table: " + curtime);
            } else {
                System.out.println("No dates found in CurrentTime table.");
            }
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
        }

    }

    public void setTime(String time){
        curtime = time;
        String query = "UPDATE CurrentTime SET CurrentDate = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, time);
            pstmt.executeUpdate();
            Thread.sleep(100);
            System.out.println("Time updated to "+curtime);
        } catch (Exception e) {
            System.out.println("SQL Exception occurred:");
            e.printStackTrace();
        }
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

    public boolean canSetTimeTo(String newtime){
        return getIsBefore(curtime, newtime);
    }
}