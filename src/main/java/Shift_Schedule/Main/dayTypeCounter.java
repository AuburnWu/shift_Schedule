package Shift_Schedule.Main;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class dayTypeCounter {

    public int[] dayTypeCount(int year ,int month) {
    	
//        int year = 2023;
//        int month = 12; 

        int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        int workdays = 0;
        int holidays = 0;

        for (int i = 0; i < daysInMonth; i++) {
            LocalDate currentDate = firstDayOfMonth.plusDays(i);

            // Check if the current date is a weekend (Saturday or Sunday)
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {                
            	holidays++;
            } else {
                workdays++;
            }
        }
        
        int[] dayCountResult = { holidays, workdays } ;
        
//        System.out.println("Workdays: " + workdays);
//        System.out.println("Holidays: " + holidays);
        
        return dayCountResult;
    }
}


