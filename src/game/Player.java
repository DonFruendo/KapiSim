package game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import production.ProductionBuilding;
import Inventory.Inventory;

public class Player
{
	private int id;
	public Inventory inventory;
	public double kaps;
	public ArrayList<ProductionBuilding> productionBuildings;
	
	public Player(int id)
	{
		this.id = id;
		inventory = new Inventory();
		productionBuildings = new ArrayList<ProductionBuilding>();
	}
	
	public int getID()
	{
		return id;
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
