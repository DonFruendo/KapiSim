package population;

import java.util.Map;

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
	Map<ProductType, int[]> productValues;
	
	public ProductType randomProductType;
	
	public Consumer(int age, int wealth, boolean male, boolean working, int workingHoursPerWeek, int freeTimeUsagePerWeek)
	{
		this.age = age;
		this.wealth = wealth;
		this.male = male;
		this.working = working;
		this.workingHoursPerWeek = workingHoursPerWeek;
		this.freeTimeUsagePerWeek = freeTimeUsagePerWeek;
		
		randomProductType = ProductType.values()[(int) (Math.random() * ProductType.values().length)];
		
		for(ProductType pType : ProductType.values())
		{
			int minPrice = (int) (20);
			int maxPrice = (int) (40);
			int minNeed = (pType == randomProductType)? 8 : 0;
			int maxNeed = (pType == randomProductType)? 10 : 5;
			productValues.put(pType, new int[]{minPrice, maxPrice, minNeed, maxNeed});
		}
	}
	
	
	public int getAmountNeededOf(ProductType product, int price)
	{
		int[] values = productValues.get(product);
		int priceSpan = values[1] - values[0];
		int needSpan = values[3] - values[2];
		int need = (int) (values[3] - ((priceSpan / needSpan) * (price - values[0])));
		return need;
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
