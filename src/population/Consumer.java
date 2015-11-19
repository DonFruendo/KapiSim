package population;

public class Consumer
{
	public int age;
	public int wealth;
	public int freeTimeUsagePerWeek;
	public int workingHoursPerWeek;
	public boolean gender;
	public boolean working;
	public boolean hasFamily;
	
	public Consumer(int age, int wealth, boolean gender, boolean working, boolean hasFamily, int workingHoursPerWeek, int freeTimeUsagePerWeek)
	{
		this.age = age;
		this.wealth = wealth;
		this.gender = gender;
		this.working = working;
		this.hasFamily = hasFamily;
		this.workingHoursPerWeek = workingHoursPerWeek;
		this.freeTimeUsagePerWeek = freeTimeUsagePerWeek;
	}
	
	
	public String toString()
	{
		return age + "\t" + wealth + "\t" + ((gender)? "male":"female") + "\t" + working + "\t" + freeTimeUsagePerWeek;
	}
	
	
}
