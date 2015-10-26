package controller;

import game.Product;

import java.util.ArrayList;

import Inventory.Inventory;
import Inventory.Inventory.Entry;

public class PlayerController
{
	private static String[] allNames = {"Perry", "Hugo", "Fritz", "Aaron", "Herbert", "Eliza"};
	private String name;
	private int id;
	private Inventory inventory;
	private int kaps;
	public ArrayList<ProductionBuildingController> productionBuildings;
	GameController gc;
	
	public PlayerController(int id, int kapital)
	{
		this.id = id;
		this.kaps = kapital;
		int r = (int)(Math.random() * allNames.length);
		this.name = allNames[r];
		inventory = new Inventory();
		productionBuildings = new ArrayList<ProductionBuildingController>();
		this.gc = GameController.getGameController();
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	// ** Financial Stuff **
	public boolean hasKapital(int kapital)
	{
		return (kaps >= kapital);
	}
	
	public int getKaps()
	{
		return kaps;
	}
	
	public void pay(int kapital)
	{
		kaps -= kapital;
	}
	
	public void getPaid(int kapital)
	{
		kaps += kapital;
	}
	
	public String toString()
	{
		return "Player[" + id + "|" + kaps + "]";
	}

	public ArrayList<Entry> getWholeInventory() {
		return inventory.getWholeInventory();
	}

	public boolean validateStock(Product tmP, int tmQ) {
		return inventory.validateStock(tmP, tmQ);
	}

	public void removeFromInventory(ArrayList<Entry> entries) {
		inventory.removeFromInventory(entries);
	}

	public void addToInventory(Product p, int quantity) {
		inventory.addToInventory(p, quantity);
	}
	
	public Inventory getInventory()
	{
		return inventory.getCopy();
	}

	public void removeFromInventory(Product product, int quantity) {
		inventory.removeFromInventory(product, quantity);
	}
}
