package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import market.Offer;
import market.ProductType;
import interfaces.controller.ArtificialIntelligenceNPC;
import interfaces.controller.Market;

public class DefaultAI extends ArtificialIntelligenceNPC
{
	ProductionOfficer productionOfficer;
	MarketAnalyzer marketAnalyzer;
	
	public DefaultAI()
	{
		super();
		productionOfficer = new ProductionOfficer();
		marketAnalyzer = new MarketAnalyzer();
	}
	
	public void bla()
	{
		System.out.println(marketAnalyzer.character + ": " + marketAnalyzer.getMarketPriceOfProduct(ProductType.Strom));
	}

	// ** Enums **
	private static enum Character
	{
		Gewinnorientiert,
		Umsatzorientiert;
		
		
		
		private static final List<Character> VALUES =
				Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();
		public static Character getRandomCharacter()
		{
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
	}
	
	// ** Classes **
	private static class Employee
	{
		protected Character character;
		public Employee()
		{
			character = Character.getRandomCharacter();
		}
	}
	
	private static class ProductionOfficer extends Employee
	{
		public ProductionOfficer()
		{
			super();
		}
	}
	
	private static class MarketAnalyzer extends Employee
	{
		Market market = Market.getController();
		
		public MarketAnalyzer()
		{
			super();
		}
		
		public int getMarketPriceOfProduct(ProductType product)
		{
			ArrayList<Offer> offers = market.getAllOffersOfType(product);
			if(offers.size() == 0)
			{
				return -1;
			}
			else
			{
				int min = offers.get(0).getCost();
				int max = offers.get(0).getCost();
				for(Offer offer : offers)
				{
					int offerCost = offer.getCost();
					if(offerCost < min)
					{
						min = offerCost;
					}
					else if(offerCost > max)
					{
						max = offerCost;
					}
				}
				switch(character)
				{
				case Gewinnorientiert:
					return max;
				case Umsatzorientiert:
					return min;
				default:
					break;
				}
				return -1;
			}
		}
	}
}
