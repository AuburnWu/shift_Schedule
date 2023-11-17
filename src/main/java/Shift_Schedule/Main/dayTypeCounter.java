package Shift_Schedule.Main;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class dayTypeCounter {

    public Map getDaliyInfo(int year ,int month) {
    	
//        int year = 2023;
//        int month = 12; 

        int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Map day_Inform = new HashMap();
        
        for (int i = 0; i < daysInMonth; i++) {
        	
        	LocalDate currentDate = firstDayOfMonth.plusDays(i);
//        	當日所需人手
        	Integer employee_Needed = 2;
        	if(currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
        		employee_Needed = 1;
        	}
//        	日期
        	day_Inform.put("DayOfMonth",currentDate.getDayOfMonth());
//        	星期幾
        	day_Inform.put("DayOfWeek",currentDate.getDayOfWeek());
        	day_Inform.put("employee_Needed",employee_Needed);        	      	        	
        }
        
        
        return day_Inform;
    }
}


