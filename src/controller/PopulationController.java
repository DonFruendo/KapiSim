package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import population.Consumer;

public class PopulationController
{
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
	
	public PopulationController()
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
			
			Consumer consumer = new Consumer(age, wealth, gender, working, false, 0, 0); // TODO ergaenzen!!
			
			allConsumers.add(consumer);
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
			age += consumer.age;
			wealth += consumer.wealth;
			if(consumer.age >= minimumWorkingAge && consumer.age <= maximumWorkingAge)
			{
				canWork++;
				if(consumer.working == false)
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
}
