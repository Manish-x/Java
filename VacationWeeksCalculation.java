import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Solution to calculate the maximum number of weeks John can spend in Hawaii.
 * 
 * John can only travel to Hawaii on Mondays and return on Sundays,
 * and his vacation spans from the first day of the start month to the last day of the end month.
 */
public class HawaiiVacation {
    
    /**
     * Calculates the maximum number of weeks John can spend in Hawaii.
     * 
     * @param Y The year of the vacation (2001-2009)
     * @param A The beginning month name (e.g., "April")
     * @param B The ending month name (e.g., "May")
     * @param W The day of the week for 1st January of that year (e.g., "Wednesday")
     * @return The maximum number of weeks John can spend in Hawaii
     */
    public static int solution(int Y, String A, String B, String W) {
        // Validate year constraint (2001-2009)
        if (Y < 2001 || Y > 2009) {
            throw new IllegalArgumentException("Year must be between 2001 and 2009 inclusive");
        }
        
        // Convert month names to month numbers
        int startMonth = getMonthNumber(A);
        int endMonth = getMonthNumber(B);
        
        // Validate that start month isn't after end month
        if (startMonth > endMonth) {
            throw new IllegalArgumentException("Start month cannot be after end month");
        }
        
        // Convert day of week name to Java's DayOfWeek
        DayOfWeek firstDayOfYear = getDayOfWeek(W);
        
        // Create date objects for the first day of start month and last day of end month
        LocalDate startDate = LocalDate.of(Y, startMonth, 1);
        LocalDate endDate = LocalDate.of(Y, endMonth, getLastDayOfMonth(Y, endMonth));
        
        // Find the first Monday on or after the start date
        LocalDate firstMonday = startDate;
        while (firstMonday.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMonday = firstMonday.plusDays(1);
        }
        
        // Find the last Sunday on or before the end date
        LocalDate lastSunday = endDate;
        while (lastSunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastSunday = lastSunday.minusDays(1);
        }
        
        // If the first Monday is after the last Sunday, no weeks are possible
        if (firstMonday.isAfter(lastSunday)) {
            return 0;
        }
        
        // Calculate the number of weeks
        long daysBetween = lastSunday.toEpochDay() - firstMonday.toEpochDay();
        return (int)(daysBetween / 7) + 1;
    }
    
    /**
     * Converts a month name to its number (1-12).
     */
    private static int getMonthNumber(String monthName) {
        switch (monthName) {
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
            default: throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }
    
    /**
     * Converts a day of week name to Java's DayOfWeek.
     */
    private static DayOfWeek getDayOfWeek(String dayName) {
        switch (dayName) {
            case "Monday": return DayOfWeek.MONDAY;
            case "Tuesday": return DayOfWeek.TUESDAY;
            case "Wednesday": return DayOfWeek.WEDNESDAY;
            case "Thursday": return DayOfWeek.THURSDAY;
            case "Friday": return DayOfWeek.FRIDAY;
            case "Saturday": return DayOfWeek.SATURDAY;
            case "Sunday": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Invalid day name: " + dayName);
        }
    }
    
    /**
     * Returns the last day of the given month in the given year.
     */
    private static int getLastDayOfMonth(int year, int month) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (year % 4 == 0) ? 29 : 28;
            default:
                return 31;
        }
    }
    
    /**
     * Example usage of the solution function.
     */
    public static void main(String[] args) {
        // Test the example from the problem statement
        System.out.println("2014, April to May, Wednesday: " + solution(2014, "April", "May", "Wednesday"));
        
        // Test the example from the problem description
        System.out.println("2008, July to September, Tuesday: " + solution(2008, "July", "September", "Tuesday"));
        
        // Test a leap year
        System.out.println("2008, February to March, Tuesday: " + solution(2008, "February", "March", "Tuesday"));
        
        // Test a single month
        System.out.println("2009, June to June, Sunday: " + solution(2009, "June", "June", "Sunday"));
    }
}