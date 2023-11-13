package Shift_Schedule.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dayTypeCounter {

	public static void main(String[] arg) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("E yyyy/MM/dd");
		
		DateFormat df = DateFormat.getDateInstance();
		         Date date = df.parse("2009/1/1");
		         Calendar calendar = Calendar.getInstance();
		         calendar.setTime(date);
		         System.out.println(sdf.format(calendar.getTime()));
		 String tm = System.currentTimeMillis().toString();
		
		System.out.println(sdf);
	}

}
