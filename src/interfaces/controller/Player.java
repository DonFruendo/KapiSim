package interfaces.controller;

import game.Product;

import java.util.ArrayList;

import market.Offer;
import market.ProductType;
import Inventory.Inventory;
import Inventory.Inventory.Entry;

public interface Player
{
	public static final long serialVersionAPI = 1L;
	
	public String getName();
	public int getID();
	public boolean hasKapital(int kapital);
	public int getKaps();
	public void pay(int kapital);
	public void getPaid(int kapital);
	public void addProductionBuilding(ProductionBuilding productionBuilding);
	public ArrayList<ProductionBuilding> getProductionBuildings();
	public void offerWasTaken(Offer offer);
	
	public String toString();
	
	// ** Inventory **
	public ArrayList<Entry> getWholeInventory();
	public boolean validateStock(Product tmP, int tmQ);
	public void removeFromInventory(ArrayList<Entry> entries);
	public Inventory getInventory();
	public void addToInventory(Product p, int quantity);
	public void removeFromInventory(Product product, int quantity);
	public ArrayList<ProductType> getAllProducts();
}
