package population;

import java.util.HashMap;
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
		
		productValues = new HashMap<ProductType, int[]>();
		for(ProductType pType : ProductType.values())
		{
			int minPrice = (int) (Math.random() * 10) + 20;		//(20);
			int maxPrice = (int) (Math.random() * 10) + 35;	//(40);
			int minNeed = (pType == randomProductType)? 10 : 0;	// 10
			int maxNeed = (pType == randomProductType)? 15 : 5;	// 15
			int[] value = new int[]{minPrice, maxPrice, minNeed, maxNeed};
			productValues.put(pType, value);
		}
	}
	
	
	public int getAmountNeededOf(ProductType product, int price)
	{
		int[] values = productValues.get(product);
		int minPrice = values[0];
		int maxPrice = values[1];
		int minNeed = values[2];
		int maxNeed = values[3];
		
		if(price <= minPrice)
		{
			return maxNeed;
		}
		else if(price >= maxPrice)
		{
			return minNeed;
		}
		else
		{
			int needSpan = maxNeed - minNeed;
			int priceSpan = maxPrice - minPrice;
			int need = (int) (maxNeed - ((needSpan / (priceSpan * 1.)) * (double)(price - minPrice)));
			return need;
		}
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
