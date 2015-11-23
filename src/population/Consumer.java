package population;

import market.ProductType;

public class Consumer
{
	private int age;
	private int wealth;
	private int freeTimeUsagePerWeek;
	private int workingHoursPerWeek;
	private boolean male;
	private boolean working;
	private Household household = null;
	
	private ProductType randomProductType;
	
	public Consumer(int age, int wealth, boolean male, boolean working, int workingHoursPerWeek, int freeTimeUsagePerWeek)
	{
		this.age = age;
		this.wealth = wealth;
		this.male = male;
		this.working = working;
		this.workingHoursPerWeek = workingHoursPerWeek;
		this.freeTimeUsagePerWeek = freeTimeUsagePerWeek;
		
		randomProductType = ProductType.values()[(int) (Math.random() * ProductType.values().length)];
	}
	
	
	public int getAge() {
		return age;
	}


	public int getWealth() {
		return wealth;
	}


	public int getFreeTimeUsagePerWeek() {
		return freeTimeUsagePerWeek;
	}


	public int getWorkingHoursPerWeek() {
		return workingHoursPerWeek;
	}


	public boolean isMale() {
		return male;
	}


	public boolean isWorking() {
		return working;
	}


	public boolean hasFamily() {
		return (household != null);
	}


	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household)
	{
		this.household = household;
	}
	

	public String toString()
	{
		return age + "\t" + wealth + "\t" + ((male)? "male":"female") + "\t" + working + "\t" + freeTimeUsagePerWeek;
	}
	
	
}
