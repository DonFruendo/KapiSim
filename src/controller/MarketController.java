package controller;

import interfaces.controller.Game;
import interfaces.controller.Market;
import interfaces.controller.Player;

import java.util.ArrayList;

import market.Offer;

/**
 * The MarketController holds methods to control market behaviour.
 * There is only one MarketController at a time, so there will be no secret marketregions or something similar.
 * @author DonFruendo
 *
 */
public class MarketController extends Market
{
	/**
	 * The market itself
	 */
	private static MarketController market;
	/**
	 * Contains every offer currently active.
	 * This also includes private offers!
	 */
	ArrayList<Offer> allOffers;
	/**
	 * A reference to the GameController
	 */
	final Game gc;
	
	/**
	 * The constructor
	 * It is private due to the behaviour of the singleton-objects. It can only be called from inside the class.
	 * 
	 * See also:
	 * {@link #getController()}
	 */
	private MarketController()
	{
		allOffers = new ArrayList<Offer>();
		this.gc = Game.getController();
	}
	
	/**
	 * Creates a new market if and only if there is none. Returns it afterwards.
	 * @return {@link #market}
	 */
	public static MarketController getController()
	{
		if(market == null)
		{
			market = new MarketController();
		}
		return market;
	}
	
	/**
	 * Register an offer.
	 * Place an offer in the market. This also contains private offers!
	 * @param offer - offer to be placed
	 */
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
	
	/**
	 * Take an offer from the market
	 * Search offer by ID and try to send it to the player.
	 * @param player - the player, who wants to receive the offer
	 * @param offerid - the ID of the offer
	 */
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
	
	/**
	 * Take an offer from the market
	 * Search by content of the offer. Note, that you can search without knowing the ID of the original offer.
	 * @param player - the player, who wants to receive the offer
	 * @param offer - the offer
	 */
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
	
	/**
	 * Try to give the offer to the player
	 * If the player mets all conditions to take the offer, the offer will now be processed
	 * @param player - the player, who wants to receive the offer
	 * @param offer - the ID of the offer
	 */
	private void processOffer(Player player, Offer offer)
	{
		int playerID = player.getID();
		if(offer.getReceiverID() == -1 || offer.getReceiverID() == playerID)
		{
			if(player.hasKapital(offer.getTotal()))
			{
				player.addToInventory(offer.getProduct(), offer.getQuantity());
				player.pay(offer.getTotal());
				gc.getPlayer(offer.getOffererID()).getPaid(offer.getTotal());
				allOffers.remove(offer);
			}
			else
			{
				gc.message(player + " has not enough kaps. " + offer.getTotal() + " needed");
			}
		}
		else
		{
			gc.message(player + " is not allowed to take this offer");
		}
	}
	
	/**
	 * Returns a list of every offer currently in the market
	 * @return list of all offers
	 */
	public ArrayList<Offer> getAllOffers()
	{
		return allOffers;
	}
}