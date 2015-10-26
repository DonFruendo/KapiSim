package controller;

import production.*;
import views.GameGUI;

public class GameController
{
	private static GameController gameController;
	private MarketController market;
	PlayerController p;
	
	GameGUI gui;
	
	LogState debugMode = LogState.Idle;
	
	private GameController()
	{
	}
	
	public void startGame()
	{
		market = new MarketController();
		p = new PlayerController(11, 100000);
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
		
		
		gui = new GameGUI();
		gui.start();
	}
	
	// ** Console interaction **
	public void message(String message)
	{
		if(LogState.Idle.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(message);
		}
	}
	
	public void Log(String message)
	{
		if(LogState.Log.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println("> " + message);
		}
	}
	
	public void Debug(String message)
	{
		if(LogState.Debug.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(">> " + message);
		}
	}
	
	public void Error(String message)
	{
		if(LogState.Error.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println("Error: " + message);
		}
	}
	// ** Console Interaction end **
	
	public MarketController getMarket()
	{
		return market;
	}
	
	public PlayerController getPlayer()
	{
		return p;
	}
	
	public PlayerController getPlayer(int ID)
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
	
	
	
	
	
	static enum LogState
	{
		Idle	(0),
		Error	(1),
		Log		(2),
		Debug	(3);
		
		int value;
		
		LogState(int value)
		{
			this.value = value;
		}
		
		public boolean show(LogState logState)
		{
			return (this.value <= logState.value);
		}
	}
}
