package game;

import java.util.ArrayList;

import Inventory.Inventory;
import production.ProductionBuilding;

public class Player
{
	private static String[] allNames = {"Perry", "Hugo", "Fritz", "Aaron", "Herbert", "Eliza"};
	private String name;
	private int id;
	public Inventory inventory;
	public double kaps;
	public ArrayList<ProductionBuilding> productionBuildings;
	
	public Player(int id)
	{
		this.id = id;
		int r = (int)(Math.random() * allNames.length);
		this.name = allNames[r];
		inventory = new Inventory();
		productionBuildings = new ArrayList<ProductionBuilding>();
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean hasKapital(double kapital)
	{
		return (kaps >= kapital);
	}
	
	public String toString()
	{
		return "Player[" + id + "|" + kaps + "]";
	}
}
