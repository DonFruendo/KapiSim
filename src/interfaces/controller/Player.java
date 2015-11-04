package interfaces.controller;

import game.Product;

import java.util.ArrayList;

import Inventory.Inventory;
import Inventory.Inventory.Entry;

public interface Player
{
	public String getName();
	public int getID();
	public boolean hasKapital(int kapital);
	public int getKaps();
	public void pay(int kapital);
	public void getPaid(int kapital);
	
	
	public String toString();
	public ArrayList<Entry> getWholeInventory();
	public boolean validateStock(Product tmP, int tmQ);
	public void removeFromInventory(ArrayList<Entry> entries);
	public Inventory getInventory();
	public void addToInventory(Product p, int quantity);
	public void removeFromInventory(Product product, int quantity);
}
