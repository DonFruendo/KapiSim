package interfaces.controller;

import java.util.ArrayList;

import controller.MarketController;
import market.Offer;
import market.ProductType;

public abstract class Market
{
	public static final long serialVersionAPI = 1L;
	
	public static Market getController()
	{
		return MarketController.getController();
	}
	public static Offer createOffer(ProductType type, int quantity, int price)
	{
		Offer offer = new Offer(Game.getController().getPlayer().getID(), type, quantity, 0, price); // ID????
		return offer;
	}

	public abstract void openMarket();
	public abstract void closeMarket();
	public abstract void registerOffer(Offer offer);
	public abstract void takeOffer(Player player, int offerid);
	public abstract void takeOffer(Player player, Offer offer);
	public abstract ArrayList<Offer> getAllOffers();
	public abstract ArrayList<Offer> getAllOffersOfType(ProductType product);
}
