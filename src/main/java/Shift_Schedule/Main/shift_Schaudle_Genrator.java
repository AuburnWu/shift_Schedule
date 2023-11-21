package Shift_Schedule.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shift_Schaudle_Genrator {

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

		ArrayList<String> resultList = new ArrayList<String>();

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
			ArrayList<Employee> employee_List = new ArrayList<Employee>();
			employee_List.add(A);
			employee_List.add(B);
			employee_List.add(C);

//			所需人數
			Integer daily_Needed = (Integer) daliyInfoMap.get("employee_Needed" + i);

//			存放當天指定假日員工用
			ArrayList<Employee> adjest_List = new ArrayList<Employee>();

//			System.out.println("employee_List.size = " + employee_List.size());

//			取出各員工指定假日
			employee_List.forEach((employee) -> {
//				String employeeName = employee.getName();
				String designateDay = employee.getDesignate_Day();
				String[] designateDayList = designateDay.split(",");
//				迴圈判斷當天是否為指定日
				for (String designateday : designateDayList) {
//					System.out.println(employee_name + "'s " + "designateDay = " + designateDay);
					if (Integer.valueOf(designateday) == dayOfMonth) {
						adjest_List.add(employee);
//						System.out.println("match = " + designateDay + " and dayOfMonth = " + dayOfMonth);
						break;
					}
				}
			});

//			判斷待指派人數是否足夠，否則以原表單指派
			if (employee_List.size() - adjest_List.size() >= daily_Needed) {
				employee_List.removeAll(adjest_List);
			} else {
//				System.out.println("employee_List.size = " +employee_List.size());
//				System.out.println("adjest_List.size = " +adjest_List.size()  );
				System.out.println("----conflict----");
			}

//			以處理好的人員表隨機分派班次
//			System.out.println("intro_List.size = " + employee_List.size());
			Map<String, ArrayList<Employee>> assignMap = ssg.getRandomEmployee(employee_List, daily_Needed);
			ArrayList<Employee> shiftList = (ArrayList<Employee>) assignMap.get("shiftList");
			ArrayList<Employee> restList = (ArrayList<Employee>) assignMap.get("restList");
//			System.out.println("shift_List.size = " + shiftList.size());

//			計算各員工休假天數
			restList.forEach((employee) -> {
				String employeeName = employee.getName();
				Integer employeeRestCount = employee.getRestCount();
//				System.out.println("S setRestCount, " + employeeName + ", " + employeeRestCount);
				if (restList.contains(employee)) {
					employee.setRestCount(employeeRestCount + 1);
				}
				System.err.println("E setRestCount, " + employeeName + ", " + employee.getRestCount());
			});

//			星期幾轉換
			String weekDay ="";
			
			switch (dayOfWeek) {
			case 1:
				weekDay = "MON";
				break;
			case 2:
				weekDay = "TUE";
				break;
			case 3:
				weekDay = "WED";
				break;
			case 4:
				weekDay = "THU";
				break;
			case 5:
				weekDay = "FRI";
				break;
			case 6:
				weekDay = "SAT";
				break;
			case 7:
				weekDay = "SUN";
				break;
			}

//			結果生成
			if (dayOfWeek == 7) {
//				System.out.println(month + "/" + dayOfMonth + " " + dayOfWeek + " morning :  " + shiftList.get(0).getName());
				resultList.add(month + "/" + dayOfMonth + " " + weekDay + " morning :  " + shiftList.get(0).getName());
				resultList.add(A.getName() + " " + A.getRestCount() + " , " + B.getName() + " " + B.getRestCount() + " , " + C.getName() + " " + C.getRestCount());
			} else {
				
//				System.out.println(month + "/" + dayOfMonth + " " + dayOfWeek + " morning :  " + shiftList.get(0).getName()
//				+ ", afternoon : " + shiftList.get(1).getName());
				resultList.add(month + "/" + dayOfMonth + " " + weekDay + " morning :  " + shiftList.get(0).getName()
						+ ", afternoon : " + shiftList.get(1).getName());
				resultList.add(A.getName() + " " + A.getRestCount() + " , " + B.getName() + " " + B.getRestCount() + " , " + C.getName() + " " + C.getRestCount());
			}

		}

		String filePath = "D:\\TempTest\\test.txt";
		ssg.generateCSV(filePath, resultList);
	}

	private Map<String, ArrayList<Employee>> getRandomEmployee(ArrayList<Employee> employeeList, Integer dailyNeeded) {
		SecureRandom random = new SecureRandom();
		Integer length = employeeList.size();
		ArrayList<Employee> shiftList = new ArrayList<Employee>();
		ArrayList<Employee> restList = new ArrayList<Employee>();
		Map<String, ArrayList<Employee>> assignMap = new HashMap<String, ArrayList<Employee>>();

		while (dailyNeeded > 0 && !employeeList.isEmpty()) {
			length = employeeList.size();
			int randomIndex = random.nextInt(length);

			Employee assignEmploeey = employeeList.get(randomIndex);

			System.out.println("assignEmploeey = " + assignEmploeey.getName());
			if (assignEmploeey.getRestCount() >= 10) {
				continue;
			} else {
				shiftList.add(assignEmploeey);
				employeeList.remove(randomIndex);
//				System.out.println("randomIndex = " + randomIndex);
//				System.out.println("assign_Emploeey = " + assign_Emploeey.getName());
				dailyNeeded--;
			}
		}

//		for(Employee employee:employeeList){
//			System.out.println("restList employee " + employee.getName());
		restList = employeeList;
//		}

		assignMap.put("shiftList", shiftList);
		assignMap.put("restList", restList);

		return assignMap;
	}

	private static void generateCSV(String filePath, ArrayList<String> data) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			// Write data
			for (int i = 0; i < data.size(); i++) {
				writer.println(data.get(i));
			}

			System.out.println("CSV file generated successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
