package controller;

import Inventory.Player;
import production.*;

public class GameController
{
	private static GameController gameController;
	MarketController market;
	Player p;
	
	private GameController()
	{
		market = new MarketController(this);
		p = new Player(11, 100000);
		Kraftwerk k1 = new Kraftwerk(p);
		Quelle q1 = new Quelle(p);
		Plantage p1 = new Plantage(p);
		Obstplantage o1 = new Obstplantage(p);
		Viehzucht  v1 = new Viehzucht(p);
		Lebensmittelproduktion l1 = new Lebensmittelproduktion(p);
		Getraenkefabrik g1 = new Getraenkefabrik(p);
		Fleischerei f1 = new Fleischerei(p);
		Eismanufaktur e1 = new Eismanufaktur(p);
		p.productionBuildings.add(k1);
		p.productionBuildings.add(q1);
		p.productionBuildings.add(o1);
		p.productionBuildings.add(v1);
		p.productionBuildings.add(p1);
		p.productionBuildings.add(l1);
		p.productionBuildings.add(g1);
		p.productionBuildings.add(f1);
		p.productionBuildings.add(e1);
		
	}
	
	// ** Console interaction **
	public void message(String message)
	{
		System.out.println(message);
	}
	
	public void Log(String message)
	{
		System.out.println("> " + message);
	}
	
	public void Debug(String message)
	{
		System.out.println(">> " + message);
	}
	
	public void Error(String message)
	{
		System.out.println("Error: " + message);
	}
	// ** Console Interaction end **
	
	public MarketController getMarket()
	{
		return market;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	public Player getPlayer(int ID)
	{
		return null;
	}
	
	public static GameController getGameController()
	{
		if(gameController == null)
		{
			gameController = new GameController();
		}
		return gameController;
	}
}
