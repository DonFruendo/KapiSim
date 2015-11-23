package controller;

import population.Household;
import market.ProductType;
import interfaces.controller.GlobalMarketAnalyzer;
import interfaces.controller.Population;

public class GlobalMarketAnalyzerController extends GlobalMarketAnalyzer {

	Population pop = Population.getController();
	
	public GlobalMarketAnalyzerController()
	{
		
	}

	@Override
	public int getAmountAskedFor(ProductType product)
	{
		int counter = 0;
		for(Household household : pop.getHouseholds())
		{
			for(ProductType productType : household.getNeededProducts())
			{
				if(productType == product)
				{
					counter++;
				}
			}
		}
		return counter;
	}

}
