package market;

import game.Player;

import java.util.ArrayList;

public class Market
{
	ArrayList<Offer> allOffers;
	
	public Market()
	{
		allOffers = new ArrayList<Offer>();
	}
	
	public void placeOffer(Offer offer)
	{
		if(!offer.offerer.inventory.validateStock(offer.getProduct(), offer.quantity))
		{
			System.out.println("Not enough " + offer.product + " in stock of " + offer.offerer);
		}
		else
		{
			double fee = offer.total / 10.;
			if(!(offer.offerer.kaps >= fee))
			{
				System.out.println("Not enough kaps to pay market fee. " + fee + " needed to offer this.");
			}
			else
			{
				offer.offerer.inventory.removeFromInventory(offer.getProduct(), offer.quantity);
				// Marktgebühren
				offer.offerer.kaps -= fee;
				allOffers.add(offer);
			}
		}
	}
	
	public void takeOffer(Player player, int offerid)
	{
		for(int i = allOffers.size()-1; i >= 0; i--)
		{
			Offer o = allOffers.get(i);
			if(o.id == offerid)
			{
				processOffer(player, o);
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
		if(o.receiver == null || o.receiver == player)
		{
			if(player.kaps >= o.total)
			{
				player.inventory.addToInventory(o.getProduct(), o.quantity);
				player.kaps -= o.total;
				o.offerer.kaps += o.total;
				allOffers.remove(o);
			}
			else
			{
				System.out.println(player + " has not enough kaps. " + o.total + " needed");
			}
		}
		else
		{
			System.out.println(player + " is not allowed to take this offer");
		}
	}
	
	public ArrayList<Offer> getAllOffers()
	{
		return allOffers;
	}
}