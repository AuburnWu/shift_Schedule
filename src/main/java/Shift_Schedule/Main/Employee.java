package Shift_Schedule.Main;

public class Employee {
	private String Name;
	private String designate_Day;
	
	public Employee(String name, String designate_Day) {
		Name = name;
		this.designate_Day = designate_Day;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDesignate_Day() {
		return designate_Day;
	}

	public void setDesignate_Day(String designate_Day) {
		this.designate_Day = designate_Day;
	}
	
	
	
}
