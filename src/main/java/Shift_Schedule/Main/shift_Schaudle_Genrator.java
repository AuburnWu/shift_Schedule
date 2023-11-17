package Shift_Schedule.Main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;


public class shift_Schaudle_Genrator {
	@SuppressWarnings("unchecked")
	public static void main(String[] arg) {
		shift_Schaudle_Genrator ssg = new shift_Schaudle_Genrator();
		dayTypeCounter dtc = new dayTypeCounter();
		ArrayList<Employee> employee_Arr = new ArrayList<Employee>();
		Employee A = new Employee("AAA","1,18,25");
		Employee B = new Employee("BBB","7,8,9");
		Employee C = new Employee("CCC","23,24,25");
		
		employee_Arr.add(A);
		employee_Arr.add(B);
		employee_Arr.add(C);
		
		Map daliyInfoMap = dtc.getDaliyInfo(2023,12);
		
		
		
		daliyInfoMap.forEach((key, value)->{
			Integer dayOfMonth = (Integer) daliyInfoMap.get("DayOfMonth");
			Integer dayOfWeek = (Integer) daliyInfoMap.get("DayOfWeek");
			for(int i = 0; i < employee_Arr.size(); i++) {
				String designate_Day = employee_Arr.get(i).getDesignate_Day();
				String employee_name = employee_Arr.get(i).getName();
				String[] designate_Day_List = designate_Day.split(designate_Day);
				
				for(String designateDay:designate_Day_List) {
					 if(Integer.valueOf(designateDay) == dayOfMonth) {
						 employee_Arr.remove(employee_name);
					 }
				}				
			}
			
			Integer daily_Needed = (Integer)daliyInfoMap.get("employee_Needed");
			ArrayList shift_List = ssg.getRandomEmployee(employee_Arr, daily_Needed);
			
			
			System.out.println(dayOfMonth + dayOfWeek + " morning :  " + shift_List.get(0)  + ", afternoon : " + shift_List.get(1));
						
		});
		
		
//		ArrayList<String> shift_Arr = new ArrayList<String>(); 
//		shift_Arr = c.getRandomEmployee(employee_Arr, 2);
//		
//		for(String employee:shift_Arr){
//			System.out.println( "shift duty = " + employee);
//		}		
	}

//	private String[] shift_Schaudle(int hoilday, int workingday) {
//		String[] shift_schedule = null;
//
//		return shift_schedule;
//	}
	
	
	private ArrayList<String> getRandomEmployee(ArrayList<Employee> employee_Arr, Integer daily_Needed) {
		StringBuilder randomSymbolString = new StringBuilder();
		SecureRandom random = new SecureRandom();
		Integer length = employee_Arr.size();
		ArrayList<String> shift_List = new ArrayList<String>();
		
		while(daily_Needed > 0 && !employee_Arr.isEmpty()) {
			length = employee_Arr.size();	
			int randomIndex = random.nextInt(length);
			
			shift_List.add(employee_Arr.get(randomIndex).getName());
			employee_Arr.remove(randomIndex);
			
			daily_Needed--;
		}

		return shift_List;
	}
}
