package controller;

import interfaces.controller.Game;
import interfaces.controller.Market;
import interfaces.controller.Player;
import interfaces.views.GameGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import language.Language;
import market.Offer;
import market.ProductType;

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
	private static int marketFee = 10;
	/**
	 * Contains every offer currently active.
	 * This also includes private offers!
	 */
	ArrayList<Offer> allOffers = new ArrayList<Offer>();
	/**
	 * Contains every offer that should be processed next tick
	 */
	ArrayList<Offer> offersToBeProcessed = new ArrayList<Offer>();
	Map<Offer, Player> offerProcessedIdMap = new HashMap<Offer, Player>();
	/**
	 * Contains every offer that should be placed next tick
	 */
	ArrayList<Offer> offersToBePlaced = new ArrayList<Offer>();
	/**
	 * Basic Products Ltd
	 */
	BasicProductsLtd basicProductsLtd = new BasicProductsLtd();
	/**
	 * A reference to the GameController
	 */
	final Game gc;
	/**
	 * A reference to the GameGUI
	 */
	GameGUI gameGUI;
	/**
	 * LanguagePack
	 */
	private static final Language lang = Game.getController().getLanguagePack();
	/**
	 * Is true, if the players can interact with the market
	 */
	boolean isOpen;
	
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
		this.gameGUI = gc.getGameGUI();
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
	
	void tick()
	{
		// TODO Update
		// ** Process offers **
		for(Offer offer : offersToBeProcessed)
		{
			Player player = offerProcessedIdMap.get(offer);
			processOffer(player, offer);
		}
		
		// ** Place offers **
		for(Offer offer : offersToBePlaced)
		{
			placeOffer(offer);
		}
	}
	
	/**
	 * Opens the market and makes offerplacing possible
	 */
	public void openMarket()
	{
		this.gameGUI = gc.getGameGUI();
		isOpen = true;
		gc.playerSignUp(basicProductsLtd);
		basicProductsLtd.initMarketEntry();
	}
	
	/**
	 * Closes the market and makes offerplacing impossible
	 */
	public void closeMarket()
	{
		isOpen = false;
	}
	
	/**
	 * Register an offer.
	 * Place an offer in the market. This also contains private offers!
	 * @param offer - offer to be placed
	 */
	public void registerOffer(Offer offer)
	{
		Player offerer = gc.getPlayer(offer.getOffererID());
		if(!offerer.validateStock(offer.getProduct(), offer.getQuantity()))
		{
			gc.message(lang.not_enough + " " + offer.getProduct() + " " + lang.in_stock_of + " " + offerer);
		}
		else
		{
			int fee = (int)(offer.getTotal() * marketFee / 100.);
			if(!(offerer.hasKapital(fee)))
			{
				gc.message(lang.not_enough + " " + lang.kaps + " " + lang.to_pay_market_fee + ". " + fee + " " + lang.needed_to_offer_this +".");
			}
			else
			{
				offersToBePlaced.add(offer);
			}
		}
	}
	
	private void placeOffer(Offer offer)
	{
		Player offerer = gc.getPlayer(offer.getOffererID());
		offerer.removeFromInventory(offer.getProduct(), offer.getQuantity());
		// Marktgebuehren
		offerer.pay((int) (offer.getTotal() * (marketFee / 100.)));
		allOffers.add(offer);
		
		gameGUI.reloadInventory();
		gameGUI.reloadMarket();
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
				//processOffer(player, offer);
				offersToBeProcessed.add(offer);
				offerProcessedIdMap.put(offer, player);
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
				//processOffer(player, o);
				offersToBeProcessed.add(o);
				offerProcessedIdMap.put(o, player);
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
				gc.getPlayer(offer.getOffererID()).offerWasTaken(offer);
			}
			else
			{
				gc.message(player + lang.has_not_enough_kaps + ". " + offer.getTotal() + " " + lang.needed);
			}
		}
		else
		{
			gc.message(player + " " + lang.is_not_allowed_to_take_this_offer);
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
	
	public ArrayList<Offer> getAllOffersOfType(ProductType product)
	{
		ArrayList<Offer> resultSet = new ArrayList<Offer>();
		for(Offer offer : allOffers)
		{
			if(offer.getProduct().type == product)
			{
				resultSet.add(offer);
			}
		}
		return resultSet;
	}
}