package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import market.Offer;
import market.ProductType;
import interfaces.controller.ArtificialIntelligenceNPC;
import interfaces.controller.GlobalMarketAnalyzer;
import interfaces.controller.Market;
import interfaces.controller.ProductionBuilding;

public class DefaultAI extends ArtificialIntelligenceNPC
{
	CEO ceo;
	ProductionOfficer productionOfficer;
	MarketAnalyzer marketAnalyzer;
	FinancialOfficer financialOfficer;
	
	public DefaultAI()
	{
		super();
		ceo = new CEO(this);
		productionOfficer = new ProductionOfficer(this);
		marketAnalyzer = new MarketAnalyzer(this);
		financialOfficer = new FinancialOfficer(this);
	}
	
	public void tick()
	{
		//System.out.println(getID() + "\t" + name + "\t" + marketAnalyzer.character + ": " + marketAnalyzer.getMarketPriceOfProduct(ProductType.Strom));
		for(ProductType production : new ArrayList<ProductType>(productionOfficer.productionPlan.keySet()))
		{
			if(marketAnalyzer.getMarketDeficit(production) <= 0)
			{
				productionOfficer.dontProduce(production);
			}
		}
		
		ProductType product = marketAnalyzer.getBestProductType();
		int bestAmount = ceo.getBestAmountToProduce(product); // TODO
		productionOfficer.produce(product, bestAmount); // TODO
		
		System.out.println(this + " would like to produce " + productionOfficer.productionPlan);
	}
	
	@Override
	public Map<ProductType, Integer> getProductionPlans()
	{
		return productionOfficer.getProductionPlans();
	}

	// ***********
	// **  End  **
	// **  of   **
	// ** Class **
	// ***********
	
	// ** Enums **
	private static enum Character
	{
		Gewinnorientiert,
		Umsatzorientiert,
		Kostendeckend;
		
		
		
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
	
	private static class CEO
	{
		DefaultAI company;
		
		public CEO(DefaultAI company)
		{
			super();
			this.company = company;
		}
		
		public int getBestAmountToProduce(ProductType product)
		{
			int maxKaps = company.financialOfficer.getBudgetFor(product);
			int maxAmountProducable = company.productionOfficer.getMaxAmountProducableOf(product, maxKaps);
			int maxAmountNeededByConsumers = company.marketAnalyzer.getMarketDeficit(product);
			
			if(maxAmountNeededByConsumers > maxAmountProducable)
			{
				return maxAmountProducable;
			}
			else
			{
				return maxAmountNeededByConsumers;
			}
		}
	}
	
	private static class ProductionOfficer extends Employee
	{
		DefaultAI company;
		private Map<ProductType, Integer> productionPlan;
		
		
		public ProductionOfficer(DefaultAI company)
		{
			super();
			this.company = company;
			productionPlan = new HashMap<ProductType, Integer>();
		}
		
		public void produce(ProductType product, int amountPerTick)
		{
			int amountAlready = (productionPlan.containsKey(product)) ? productionPlan.get(product) : 0;
			productionPlan.put(product, amountAlready + amountPerTick);
		}
		
		public void dontProduce(ProductType product)
		{
			productionPlan.remove(product);
		}
		
		public int getProductionCostOfProduct(ProductType product)
		{
			int prodCost = 0;
			for(ProductionBuilding prodBuilding : company.getProductionBuildings())
			{
				if(prodBuilding.getPossibleTypes().contains(product))
				{
					prodCost = prodBuilding.getProductionCost(product);
					break;
				}
			}
			return prodCost;
		}
		
		public int getMaxAmountProducableOf(ProductType product, int kaps)
		{
			int costPerPiece = getProductionCostOfProduct(product);
			int result = (int) (kaps / (costPerPiece * 1.)); 
			return result;
		}
		
		public Map<ProductType, Integer> getProductionPlans()
		{
			
			return productionPlan;
		}
	}
	
	private static class MarketAnalyzer extends Employee
	{
		DefaultAI company;
		Market market = Market.getController();
		GlobalMarketAnalyzer gma = GlobalMarketAnalyzer.getGlobalMarketAnalyzer();
		
		public MarketAnalyzer(DefaultAI company)
		{
			super();
			this.company = company;
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
				case Kostendeckend:
					return company.productionOfficer.getProductionCostOfProduct(product);
				default:
					break;
				}
				return -1;
			}
		}
		
		public ProductType getBestProductType() // bold saying
		{
			int maxGap = Integer.MIN_VALUE;
			ProductType bestProduct = ProductType.values()[0];
			for(ProductType product : ProductType.values())
			{
				int gap = getMarketDeficit(product);
				if(gap > maxGap)
				{
					maxGap = gap;
					bestProduct = product;
				}
			}
			return bestProduct;
		}
		
		public int getMarketDeficit(ProductType product)
		{
			int price = getMarketPriceOfProduct(product);
			int amountAsked = gma.getAmountAskedFor(product, price);
			int amountProduced = gma.getAmountProduced(product, price);
			return amountAsked - amountProduced;
		}
	}
	
	private static class FinancialOfficer extends Employee
	{
		DefaultAI company;
		public FinancialOfficer(DefaultAI company)
		{
			super();
			this.company = company;
		}
		
		public int getBudgetFor(ProductType product)
		{
			return (int) (company.getKaps() - (company.getKaps() * 0.1));
		}
	}
}
