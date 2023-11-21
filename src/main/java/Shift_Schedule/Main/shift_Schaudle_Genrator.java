package Shift_Schedule.Main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shift_Schaudle_Genrator {
	@SuppressWarnings("unchecked")
	public static void main(String[] arg) {
		shift_Schaudle_Genrator ssg = new shift_Schaudle_Genrator();
		dayTypeCounter dtc = new dayTypeCounter();
		

		Employee A = new Employee("AAA", "1,18,25");
		Employee B = new Employee("BBB", "7,8,9");
		Employee C = new Employee("CCC", "23,24,25");

		

//		年月份設定
		Integer year = 2023;
		Integer month = 12;
		
//		日期資訊
		Map daliyInfoMap = dtc.getDaliyInfo(year, month);
//		該月份天數
		Integer daysInMonth = (Integer) daliyInfoMap.get("daysInMonth");

//		逐日生成班表
		for (int i = 0; i < daysInMonth; i++) {
//			幾號
			Integer dayOfMonth = (Integer) daliyInfoMap.get("DayOfMonth" + i);
//			星期幾
			Integer dayOfWeek = (Integer) daliyInfoMap.get("DayOfWeek" + i);

//			System.out.println("dayOfMonth = " + dayOfMonth);
//			System.out.println("dayOfWeek = " + dayOfWeek);
			
			
//			所需人數
			Integer daily_Needed = (Integer) daliyInfoMap.get("employee_Needed" + i);
			
//			存放當天指定假日員工用
			ArrayList<Employee> adjest_List = new ArrayList<>();
			ArrayList<Employee> employee_List = new ArrayList<Employee>();
			employee_List.add(A);
			employee_List.add(B);
			employee_List.add(C);
//			System.out.println("employee_List.size = " + employee_List.size());
//			比對各員工指定假日
			employee_List.forEach((employee) -> {
				String employee_name = employee.getName();
				String designate_Day = employee.getDesignate_Day();
				String[] designate_Day_List = designate_Day.split(",");

				for (String designateDay : designate_Day_List) {
//					System.out.println(employee_name + "'s " + "designateDay = " + designateDay);
//					判斷當天是否為指定日
					if (Integer.valueOf(designateDay) == dayOfMonth) {
						adjest_List.add(employee);
//						System.out.println("match = " + designateDay + " and dayOfMonth = " + dayOfMonth);
						break;
					}
				}
			});
//			判斷待指派人數是否足夠，否則以原表單指派
			if (employee_List.size() - adjest_List.size() >= daily_Needed) {
				employee_List.removeAll(adjest_List);	
			}else {
//				System.out.println("employee_List.size = " +employee_List.size());
//				System.out.println("adjest_List.size = " +adjest_List.size()  );
				System.out.println("----conflict----");
			}
			
//			以處理好的人員表隨機分派班次
//			System.out.println("intro_List.size = " + employee_List.size());
			Map assignMap = ssg.getRandomEmployee(employee_List, daily_Needed);
			ArrayList shiftList = (ArrayList)assignMap.get("shiftList");
//			System.out.println("shift_List.size = " + shiftList.size());
			
//			結果
			if(dayOfWeek == 7) {
				System.out.println(month + "/" + dayOfMonth + " " + dayOfWeek + " morning :  " + shiftList.get(0));
			}else {
				System.out.println(month + "/" + dayOfMonth + " " + dayOfWeek + " morning :  " + shiftList.get(0)
				+ ", afternoon : " + shiftList.get(1));
			}
			

		}

	}

	private Map<String,ArrayList> getRandomEmployee(ArrayList<Employee> employeeList, Integer dailyNeeded) {
		StringBuilder randomSymbolString = new StringBuilder();
		SecureRandom random = new SecureRandom();
		Integer length = employeeList.size();
		ArrayList<String> shiftList = new ArrayList<String>();
		ArrayList<String> restList = new ArrayList<String>();
		Map result = new HashMap<>();
		
		while (dailyNeeded > 0 && !employeeList.isEmpty()) {
			length = employeeList.size();
			int randomIndex = random.nextInt(length);
		
			String assign_Emploeey = employeeList.get(randomIndex).getName();
			
//			System.out.println("randomIndex = " + randomIndex);
			System.out.println("assign_Emploeey = " + assign_Emploeey);
			
			shiftList.add(assign_Emploeey);
			employeeList.remove(randomIndex);

			dailyNeeded--;
		}
		
		for(Employee employee:employeeList){
			restList.add(employee.getName());
		}
		
		result.put("shiftList",shiftList);
		result.put("restList",restList);

		return result;
	}
}
