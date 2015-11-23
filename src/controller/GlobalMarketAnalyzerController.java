package controller;

import java.util.ArrayList;
import java.util.Map;

import population.Household;
import market.ProductType;
import interfaces.controller.ArtificialIntelligenceNPC;
import interfaces.controller.Game;
import interfaces.controller.GlobalMarketAnalyzer;
import interfaces.controller.Player;
import interfaces.controller.Population;

public class GlobalMarketAnalyzerController extends GlobalMarketAnalyzer {

	Population pop;
	
	public GlobalMarketAnalyzerController()
	{
		pop = Population.getController();
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
	
	@Override
	public int getAmountProduced(ProductType product)
	{
		int counter = 0;
		ArrayList<Player> allPlayers = Game.getController().getPlayers();
		for(Player player : allPlayers)
		{
			if(player instanceof ArtificialIntelligenceNPC)
			{
				ArtificialIntelligenceNPC ai = (ArtificialIntelligenceNPC) player;
				Map<ProductType, Integer> productionPlan = ai.getProductionPlans();
				if(productionPlan.containsKey(product))
				{
					counter += productionPlan.get(product);
				}
			}
		}
		return counter;
	}
	
	@Override
	public void signUp(Player player)
	{
		
	}
}
