package Shift_Schedule.Main;

public class Employee {
	private String Name;			//員工名子
	private String DesignateDay;	//指定休假日
	private Integer RestCount;		//休假日計數
	
	public Integer getRestCount() {
		return RestCount;
	}

	public void setRestCount(Integer restCount) {
		RestCount = restCount;
	}

	public Employee(String name, String designate_Day) {
		Name = name;
		this.DesignateDay = designate_Day;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDesignate_Day() {
		return DesignateDay;
	}

	public void setDesignate_Day(String designate_Day) {
		this.DesignateDay = designate_Day;
	}
	
	
	
}
