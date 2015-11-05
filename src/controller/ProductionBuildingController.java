package controller;

import interfaces.controller.Game;
import interfaces.controller.Player;
import interfaces.controller.ProductionBuilding;

import java.util.ArrayList;

import Inventory.Inventory.Entry;
import game.Product;
import market.ProductType;
import market.ProductType.Dp;

/**
 * Superclass for every ProductionBuilding
 * <p>
 * Implements whole functionality of every ProductionBuilding. The subclasses only need to define, 
 * which {@link market.ProductType}s they are able to produce.
 * @author DonFruendo
 *
 */
public abstract class ProductionBuildingController implements ProductionBuilding
{
	/**
	 * References the player, which owns this Building
	 */
	Player owner;
	/**
	 * Defines the area, covered by that building
	 */
	int area;
	/**
	 * Defines the number of workers currently employed by that building
	 */
	int worker;
	/**
	 * Contains all {@link market.ProductType}s, that building is able to produce.
	 */
	protected final ArrayList<ProductType> possible;
	protected final ArrayList<Integer> cost;
	/**
	 * References the GameController
	 */
	final Game gc;
	
	/**
	 * The Constructor
	 * initialises all attributes
	 * @param owner
	 */
	public ProductionBuildingController(Player owner)
	{
		this.owner = owner;
		this.possible = new ArrayList<ProductType>();
		this.cost = new ArrayList<Integer>();
		this.gc = Game.getController();
	}
	
	protected void addPossible(ProductType product, int cost)
	{
		this.possible.add(product);
		this.cost.add(cost);
	}
	
	int getPositionOf(ProductType product)
	{
		for(int i = 0; i < possible.size(); i++)
		{
			if(possible.get(i) == product)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the cost of production of one Element of {@code product}
	 * 
	 * @param product - {@link market.ProductType}
	 * @return cost
	 */
	public int getProductionCost(ProductType product)
	{
		return cost.get(getPositionOf(product));
	}
	
	/**
	 * Produces an new Product
	 * See: {@link #produce(Product, int)}
	 * @param pType
	 * @param quality
	 * @param quantity
	 */
	public void produce(ProductType pType, int quality, int quantity)
	{
		produce(new Product(pType, quality), quantity);
	}
	
	/**
	 * Produces a new Product
	 * times the quantity
	 * @param product
	 * @param quantity
	 */
	public void produce(Product product, int quantity)
	{
		if(productionIsPossible(product.type))
		{
			int kapsNeeded = getProductionCost(product.type) * quantity;
			if(owner.hasKapital(kapsNeeded))
			{
				boolean check = true;
				ArrayList<Entry> entries = new ArrayList<Entry>();
				for(Dp dp : product.type.getDependencies())
				{
					Product tmP = new Product(dp.getType(), 0);
					int tmQ = (int)(dp.getValue() * quantity);
					tmQ += (dp.getValue() * quantity > tmQ)? 1: 0;
					if(!owner.validateStock(tmP, tmQ))
					{
						check = false;
						gc.message(owner + " is missing " + tmQ + " " + tmP);
						break;
					}
					else
					{
						entries.add(new Entry(tmP, tmQ));
						gc.Debug(owner + " has " + tmQ + " " + tmP + " or even more!");
					}
				}
				
				if(check)
				{
					owner.pay(kapsNeeded);
					owner.removeFromInventory(entries);
					owner.addToInventory(product, quantity);
					if(entries.size() > 0)
						gc.message(entries + " wurden aus dem Lager entnommen");
					gc.message(product + " wurde " + quantity + " mal hergestellt (" + kapsNeeded/100. + " Kaps)");
					gc.Debug(product + " " + quantity);
					gc.Debug(owner.getInventory().toString());
				}
			}
			else
			{
				gc.message(owner + " has not enough kaps to produce " + product);
			}
		}
	}

	/**
	 * 
	 * @param pType
	 * @return if this building supports production of {@code pType}
	 */
	boolean productionIsPossible(ProductType pType)
	{
		return possible.contains(pType);
	}
	
	/**
	 * @return all possible {@link market.ProductType}s, that can be produced in this building
	 */
	public ArrayList<ProductType> getPossibleTypes()
	{
		return possible;
	}
}
