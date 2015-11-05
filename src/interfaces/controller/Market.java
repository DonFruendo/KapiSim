package interfaces.controller;

import java.util.ArrayList;

import controller.MarketController;
import market.Offer;

public abstract class Market
{
	public static final long serialVersionAPI = 1L;
	
	public static Market getController()
	{
		return MarketController.getController();
	}
	public abstract void placeOffer(Offer offer);
	public abstract void takeOffer(Player player, int offerid);
	public abstract void takeOffer(Player player, Offer offer);
	public abstract ArrayList<Offer> getAllOffers();
}
