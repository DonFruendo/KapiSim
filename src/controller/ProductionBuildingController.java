package controller;

import java.util.ArrayList;

import production.IProductionBuilding;
import Inventory.Inventory.Entry;
import game.Product;
import market.ProductType;
import market.ProductType.Dp;

public abstract class ProductionBuildingController implements IProductionBuilding
{
	PlayerController owner;
	int m2;
	int worker;
	protected final ArrayList<ProductType> possible;
	final GameController gc;
	
	public ProductionBuildingController(PlayerController owner)
	{
		this.owner = owner;
		this.possible = new ArrayList<ProductType>();
		this.gc = GameController.getGameController();
	}
	
	int getProductionCost(ProductType product)
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
		case Brötchen:
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
		case Öl:
			break;
		default:
			break;
		}
		return 000;
	}
	
	public void produce(ProductType p, int quality, int quantity)
	{
		produce(new Product(p, quality), quantity);
	}
	
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

	boolean productionIsPossible(ProductType p)
	{
		return possible.contains(p);
	}
	
	public ArrayList<ProductType> getPossibleTypes()
	{
		return possible;
	}
}
