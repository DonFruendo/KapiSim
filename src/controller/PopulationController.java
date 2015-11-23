package controller;

import interfaces.controller.Population;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import population.Consumer;
import population.Household;

public class PopulationController extends Population
{
	private static PopulationController controller;
	
	ArrayList<Household> allHouseHolds = new ArrayList<Household>();
	ArrayList<Consumer> allConsumers = new ArrayList<Consumer>();
	Map<Integer, Integer> ageDistribution = new HashMap<Integer, Integer>();
	int population;
	int minAge = 0;
	int maxAge = 100;
	int stepSize = 5;
	int defaultAge = 50;
	int peopleAtMinAge = 3000;
	int peopleAtDefAge = 7000;
	int peopleAtMaxAge = 50;
	
	int minimumWorkingAge = 15;
	int maximumWorkingAge = 65;
	int unemploymentRatePercent = 8;
	
	int findSomeoneToLovePercentage = 40;
	int maximumChildAge = 18;
	int noChildPerCouplePercentage;	// ??
	int oneChildPerCouplePercentage = 30;
	int twoChildrenPerCouplePercentage = 50;
	int moreThanTwoChildrenPerCouplePercentage = 10;
	
	private PopulationController()
	{
		int wholePopulation = 0;
		for(int i = minAge; i <= maxAge; i += stepSize)
		{
			int chance = 0;
			if(i < defaultAge)
			{
				chance = peopleAtDefAge - ((peopleAtDefAge - peopleAtMinAge) / (defaultAge - minAge)) * (defaultAge - i);
			}
			else if(i > defaultAge)
			{
				chance = peopleAtDefAge + ((peopleAtMaxAge - peopleAtDefAge) / (maxAge - defaultAge)) * (i - defaultAge);
			}
			else // i == defaultAge
			{
				chance = peopleAtDefAge;
			}
			wholePopulation += chance;
			System.out.println(i + "\t" + chance);
			ageDistribution.put(i, chance);
		}
		System.out.println("Gesamt: " + wholePopulation);
		population = wholePopulation;
	}
	
	public static PopulationController getController()
	{
		if(controller == null)
		{
			controller = new PopulationController();
		}
		return controller;
	}
	
	public void populate()
	{
		for(int i = 0; i < population; i++)
		{
			// ** Age Calculation **
			int age;
			double randomNumber = Math.random() * population;
			int l = 0;
			int kumPop = 0;
			while(true)
			{
				kumPop += ageDistribution.get(l);
				if(kumPop >= randomNumber)
				{
					age = (int) ((Math.random() * stepSize) + l);
					break;
				}
				else
				{
					l += stepSize;
				}
			}
			
			// ** Wealth Calculation **
			// TODO too easy...
			int wealth = (int) (Math.random() * 10);
			
			
			// ** Gender Calculation **
			boolean gender = (Math.random() > 0.5);
			
			// ** Working Calculation **
			// TODO too easy...
			boolean working;
			if(age < minimumWorkingAge || age > maximumWorkingAge)
			{
				working = false;
			}
			else
			{
				working = (Math.random() * 100) > unemploymentRatePercent;
			}
			
			Consumer consumer = new Consumer(age, wealth, gender, working, 0, 0); // TODO ergaenzen!!
			
			allConsumers.add(consumer);
		}
		
		// ** Houshold Creation **
		ArrayList<Consumer> underageAndHomeless = new ArrayList<Consumer>();
		ArrayList<Consumer> femaleAndManless = new ArrayList<Consumer>();
		ArrayList<Consumer> maleAndWifeless = new ArrayList<Consumer>();
		
		for(Consumer consumer : allConsumers)
		{
			if(consumer.hasFamily())
			{
				break;
			}
			
			if(consumer.getAge() < maximumChildAge)
			{
				underageAndHomeless.add(consumer);
				break;
			}
			Household household = new Household();
			
			if(consumer.isMale())
			{
				if((Math.random() * 100) > findSomeoneToLovePercentage)
				{
					household.addToHousehold(consumer);
					if(femaleAndManless.size() > 0)
					{
						household.addToHousehold(femaleAndManless.get((int) (Math.random() * femaleAndManless.size())));
					}
				}
				else
				{
					maleAndWifeless.add(consumer);
				}
			}
			else
			{
				if((Math.random() * 100) > findSomeoneToLovePercentage)
				{
					household.addToHousehold(consumer);
					if(maleAndWifeless.size() > 0)
					{
						household.addToHousehold(maleAndWifeless.get((int) (Math.random() * maleAndWifeless.size())));
					}
				}
				else
				{
					femaleAndManless.add(consumer);
				}
			}
			if(household.getAmountOfFamiliyMembers() != 0)
			{
				allHouseHolds.add(household);
			}
		}
		
		// Female households
		for(Consumer consumer : femaleAndManless)
		{
			if(consumer.hasFamily())
			{
				break;
			}
			Household household = new Household();
			household.addToHousehold(consumer);
			allHouseHolds.add(household);
		}
		
		// Male households
		for(Consumer consumer : maleAndWifeless)
		{
			if(consumer.hasFamily())
			{
				break;
			}
			Household household = new Household();
			household.addToHousehold(consumer);
			allHouseHolds.add(household);
		}
		
		
		// Adoption
		for(Consumer underagedHomeless : underageAndHomeless)
		{
			if(underagedHomeless.hasFamily())
			{
				break;
			}
			Household randomHousehold = allHouseHolds.get((int) (Math.random() * allHouseHolds.size()));
			randomHousehold.addToHousehold(underagedHomeless);
		}
	}
	
	public void printPopulation()
	{
		System.out.println("Age\tWealth\tGender\tworking");
		
		double age = 0, wealth = 0;
		int canWork = 0, notWorking = 0;
		for(Consumer consumer : allConsumers)
		{
			System.out.println(consumer);
			age += consumer.getAge();
			wealth += consumer.getWealth();
			if(consumer.getAge() >= minimumWorkingAge && consumer.getAge() <= maximumWorkingAge)
			{
				canWork++;
				if(consumer.isWorking() == false)
				{
					notWorking++;
				}
			}
		}
		
		age /= population;
		wealth /= population;
		System.out.println("---\t-\t------\t-----");
		System.out.println((int)(age) + "\t" + (int)(wealth));
		System.out.println();
		System.out.println("The average Consumer is " + age + " years old and has a wealth of " + wealth + ".");
		System.out.println("There are " + population + " people in this population.");
		System.out.println("Out of those " + canWork + " people that can work " + notWorking + "(" + (Math.round((notWorking / (population *1.)) * 10000.) / 100.) + "%) are unemployed.");
		
	}
	
	public ArrayList<Household> getHouseholds()
	{
		return allHouseHolds;
	}
}
