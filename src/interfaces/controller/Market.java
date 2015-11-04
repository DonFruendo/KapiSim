package interfaces.controller;

import java.util.ArrayList;

import market.Offer;

public interface Market
{
	public void placeOffer(Offer offer);
	public void takeOffer(Player player, int offerid);
	public void takeOffer(Player player, Offer offer);
	public ArrayList<Offer> getAllOffers();
}
