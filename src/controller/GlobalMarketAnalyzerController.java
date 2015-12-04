package controller;

import java.util.ArrayList;
import java.util.Map;

import interfaces.controller.ArtificialIntelligenceNPC;
import interfaces.controller.Game;
import interfaces.controller.GlobalMarketAnalyzer;
import interfaces.controller.Player;
import interfaces.controller.Population;
import market.ProductType;
import population.Consumer;

public class GlobalMarketAnalyzerController extends GlobalMarketAnalyzer {

	Population pop;
	
	public GlobalMarketAnalyzerController()
	{
		pop = Population.getController();
	}

	@Override
	public int getAmountAskedFor(ProductType product, int price)
	{
		int counter = 0;
		for(Consumer consumer : pop.getConsumers())
		{
			counter += consumer.getAmountNeededOf(product, price);
		}
		return counter;
	}
	
	@Override
	public int getAmountProduced(ProductType product, int price)
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
