package controller;

import java.util.ArrayList;

import market.Offer;
import Inventory.Player;

public class MarketController
{
	ArrayList<Offer> allOffers;
	GameController gc;
	
	public MarketController(GameController gc)
	{
		allOffers = new ArrayList<Offer>();
		this.gc = GameController.getGameController();
	}
	
	public void placeOffer(Offer offer)
	{
		Player offerer = gc.getPlayer(offer.getOffererID());
		if(!offerer.validateStock(offer.getProduct(), offer.getQuantity()))
		{
			gc.message("Not enough " + offer.getProduct() + " in stock of " + offerer);
		}
		else
		{
			int fee = (int)(offer.getTotal() / 10);
			if(!(offerer.hasKapital(fee)))
			{
				gc.message("Not enough kaps to pay market fee. " + fee + " needed to offer this.");
			}
			else
			{
				offerer.removeFromInventory(offer.getProduct(), offer.getQuantity());
				// Marktgebühren
				offerer.pay(fee);
				allOffers.add(offer);
			}
		}
	}
	
	public void takeOffer(Player player, int offerid)
	{
		for(int i = allOffers.size()-1; i >= 0; i--)
		{
			Offer offer = allOffers.get(i);
			if(offer.getId() == offerid)
			{
				processOffer(player, offer);
			}
		}
	}
	
	public void takeOffer(Player player, Offer offer)
	{
		for(int i = allOffers.size()-1; i >= 0; i--)
		{
			Offer o = allOffers.get(i);
			if(o.equals(offer))
			{
				processOffer(player, o);
			}
		}
	}
	
	private void processOffer(Player player, Offer o)
	{
		int playerID = player.getID();
		if(o.getReceiverID() == -1 || o.getReceiverID() == playerID)
		{
			if(player.hasKapital(o.getTotal()))
			{
				player.addToInventory(o.getProduct(), o.getQuantity());
				player.pay(o.getTotal());
				gc.getPlayer(o.getOffererID()).getPaid(o.getTotal());
				allOffers.remove(o);
			}
			else
			{
				gc.message(player + " has not enough kaps. " + o.getTotal() + " needed");
			}
		}
		else
		{
			gc.message(player + " is not allowed to take this offer");
		}
	}
	
	public ArrayList<Offer> getAllOffers()
	{
		return allOffers;
	}
}