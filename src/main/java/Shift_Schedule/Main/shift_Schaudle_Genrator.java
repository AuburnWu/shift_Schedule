package Shift_Schedule.Main;

import java.security.SecureRandom;
import java.util.ArrayList;


public class shift_Schaudle_Genrator {
	public static void main(String[] arg) {
		shift_Schaudle_Genrator c = new shift_Schaudle_Genrator();
		ArrayList<Employee> employee_Arr = new ArrayList<Employee>();
		Employee A = new Employee("AAA","1,18,25");
		Employee B = new Employee("BBB","7,8,9");
		Employee C = new Employee("CCC","23,24,25");
		
		employee_Arr.add(A);
		employee_Arr.add(B);
		employee_Arr.add(C);
		
		ArrayList<String> shift_Arr = new ArrayList<String>(); 
		shift_Arr = c.getRandomEmployee(employee_Arr, 2);
		
		for(String employee:shift_Arr){
			System.out.println( "shift duty = " + employee);
		}
		
		
	}

	private String[] shift_Schaudle(int hoilday, int workingday) {
		String[] shift_schedule = null;

		return shift_schedule;
	}
	
	
	private ArrayList<String> getRandomEmployee(ArrayList<Employee> employee_Arr, Integer daily_Needed) {
		StringBuilder randomSymbolString = new StringBuilder();
		SecureRandom random = new SecureRandom();
		Integer length = employee_Arr.size();
		ArrayList<String> shift_Arr = new ArrayList<String>();
		
		while(daily_Needed > 0 && !employee_Arr.isEmpty()) {
			length = employee_Arr.size();	
			int randomIndex = random.nextInt(length);
			
			shift_Arr.add(employee_Arr.get(randomIndex).getName());
			employee_Arr.remove(randomIndex);
			
			daily_Needed--;
		}

		return shift_Arr;
	}
}
