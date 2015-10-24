package production;

import java.util.ArrayList;

import Inventory.Inventory.Entry;
import game.Player;
import game.Product;
import market.ProductType;
import market.ProductType.Dp;

public abstract class ProductionBuilding implements IProductionBuilding
{
	Player owner;
	int m2;
	int worker;
	final ArrayList<ProductType> possible;
	
	public ProductionBuilding(Player owner)
	{
		this.owner = owner;
		this.possible = new ArrayList<ProductType>();
	}
	
	double getProductionCost(ProductType product)
	{
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
			return 1.80;
		case Strom:
			return 0.03;
		case Wasser:
			return 0.02;
		default:
			return 0.00;
		}
		return 0.00;
	}
	
	public void produce(ProductType p, int quality, int quantity)
	{
		produce(new Product(p, quality), quantity);
	}
	
	public void produce(Product p, int quantity)
	{
		if(productionIsPossible(p.type))
		{
			double kapsNeeded = getProductionCost(p.type) * quantity;
			if(owner.hasKapital(kapsNeeded))
			{
				boolean check = true;
				ArrayList<Entry> entries = new ArrayList<Entry>();
				for(Dp dp : p.type.getDependencies())
				{
					Product tmP = new Product(dp.getType(), 0);
					int tmQ = (int)(dp.getValue() * quantity);
					tmQ += (dp.getValue() * quantity > tmQ)? 1: 0;
					if(!owner.inventory.validateStock(tmP, tmQ))
					{
						check = false;
						System.out.println(owner + " is missing " + tmQ + " " + tmP);
						break;
					}
					else
					{
						entries.add(new Entry(tmP, tmQ));
						System.out.println(owner + " has " + tmQ + " " + tmP + " or even more!");
					}
				}
				
				if(check)
				{
					owner.kaps -= kapsNeeded;
					owner.inventory.removeFromInventory(entries);
					owner.inventory.addToInventory(p, quantity);
					System.out.println(p + " " + quantity);
					System.out.println(owner.inventory);
				}
			}
			else
			{
				System.out.println(owner + " has not enough kaps to produce " + p);
			}
		}
	}

	boolean productionIsPossible(ProductType p)
	{
		return possible.contains(p);
	}
	
	public ArrayList<ProductType> getPossibleTypes()
	{
		return possible;
	}
}
