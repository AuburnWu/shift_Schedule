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
//        System.out.println("firstDayOfMonth = " + firstDayOfMonth);
//        System.out.println("daysInMonth = " + daysInMonth);
        Map day_Inform = new HashMap();
        int weekendCount = 0; 
        		
        for (int i = 0; i < daysInMonth; i++) {
        	
        	LocalDate currentDate = firstDayOfMonth.plusDays(i);
//        	當日所需人手
        	Integer employee_Needed = 2;
        	
        	if(currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
        		weekendCount++;
        	}
        	
        	if(currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
        		employee_Needed = 1;
        	}
        	
//        	System.out.println("DayOfMonth = " + currentDate.getDayOfMonth());
//        	System.out.println("DayOfWeek = " + currentDate.getDayOfWeek().getValue() );
//        	System.out.println("employee_Needed = " + employee_Needed);
        	
//        	日期
        	day_Inform.put("DayOfMonth" + i,currentDate.getDayOfMonth());
//        	星期幾
        	day_Inform.put("DayOfWeek" + i,currentDate.getDayOfWeek().getValue());
//        	day_Inform.put("DayOfWeek",currentDate.getDayOfWeek());
        	day_Inform.put("employee_Needed" + i,employee_Needed);     
        	
        }
        day_Inform.put("weekendCount", weekendCount); 
        day_Inform.put("daysInMonth", daysInMonth);
        
        return day_Inform;
    }
}


