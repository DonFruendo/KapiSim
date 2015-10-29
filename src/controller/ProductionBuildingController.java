package controller;

import java.util.ArrayList;

import production.IProductionBuilding;
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
public abstract class ProductionBuildingController implements IProductionBuilding
{
	/**
	 * References the player, which owns this Building
	 */
	PlayerController owner;
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
	/**
	 * References the GameController
	 */
	final GameController gc;
	
	/**
	 * The Constructor
	 * initialises all attributes
	 * @param owner
	 */
	public ProductionBuildingController(PlayerController owner)
	{
		this.owner = owner;
		this.possible = new ArrayList<ProductType>();
		this.gc = GameController.getGameController();
	}
	
	/**
	 * Returns the cost of production of one Element of {@code product}
	 * 
	 * @param product - {@link market.ProductType}
	 * @return cost
	 */
	static public int getProductionCost(ProductType product)
	{
		// TODO Preise ergänzen
		switch(product)
		{
		case Apfel:
			break;
		case Banane:
			break;
		case Bananeneis:
			break;
		case Getreide:
			break;
		case Holz:
			break;
		case Milch:
			break;
		case Saatgut:
			break;
		case Steine:
			return 180;
		case Strom:
			return 003;
		case Wasser:
			return 002;
		case Apfelsaft:
			break;
		case Birne:
			break;
		case Bonbon:
			break;
		case Brot:
			break;
		case Broetchen:
			break;
		case Ei:
			break;
		case Erdbeere:
			break;
		case Erdbeereis:
			break;
		case Hackfleisch:
			break;
		case Huhn:
			break;
		case Kaffeebohne:
			break;
		case Kaffeeeis:
			break;
		case Kaffeepulver:
			break;
		case Kakao:
			break;
		case Kakaopulver:
			break;
		case KapiCola:
			break;
		case Kartoffel:
			break;
		case Kautschuk:
			break;
		case Lamm:
			break;
		case Lammfleisch:
			break;
		case Mehl:
			break;
		case Orange:
			break;
		case Orangeneis:
			break;
		case Orangensaft:
			break;
		case Rind:
			break;
		case Rindfleisch:
			break;
		case Schokoeis:
			break;
		case Schokoriegel:
			break;
		case Schwein:
			break;
		case Schweinefleisch:
			break;
		case Tierfutter:
			break;
		case Weintraube:
			break;
		case Wurst:
			break;
		case Zitrone:
			break;
		case Zitroneneis:
			break;
		case Zucker:
			break;
		case Zuckerrohr:
			break;
		case Oel:
			break;
		default:
			break;
		}
		return 000;
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
