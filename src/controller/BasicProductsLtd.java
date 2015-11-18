package controller;

import interfaces.controller.Game;
import interfaces.controller.Market;

import java.util.ArrayList;

import game.Product;
import language.Language;
import market.Offer;
import market.ProductType;
import Inventory.Inventory;
import Inventory.Inventory.Entry;

public class BasicProductsLtd extends PlayerController {

	private static ArrayList<ProductType> grundstoffe;
	private static final Language lang = Game.getController().getLanguagePack();
	
	private static final int quantity = 10;
	
	public BasicProductsLtd() {
		super();
		this.name = lang.basicProductsLtd;
		grundstoffe = new ArrayList<ProductType>();
		for(ProductType product : ProductType.values())
		{
			if(product.getType() == ProductType.Type.Grundstoff)
				grundstoffe.add(product);
		}
		resetInventory();
	}
	
	public void initMarketEntry()
	{
		Market market = Market.getController();
		for(ProductType product : grundstoffe)
		{
			market.placeOffer(new Offer(this.getID(), product, quantity, 0, cost(product)));
		}
	}
	
	private int cost(ProductType product)
	{
		switch(product)
		{
		case Strom:
		case Saatgut:
		case Wasser:
			return 27;
		case Holz:
			return 7500;
		//case Stahl:
			//return 13500;
		case Steine:
			return 2700;
		default:
			return 1000000;
		}
	}
	
	public void resetInventory()
	{
		this.inventory = new Inventory();
		for(ProductType product : grundstoffe)
		{
			inventory.addtoInventory(new Entry(new Product(product, 0), quantity));
		}
	}
	
	@Override
	public void getPaid(int kaps)
	{
		resetInventory();
	}
	
	@Override
	public void offerWasTaken(Offer offer)
	{
		Market.getController().placeOffer(new Offer(this.getID(), offer.getProduct(), quantity, 27)); // TODO
	}
}
