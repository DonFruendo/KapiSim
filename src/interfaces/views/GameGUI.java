package interfaces.views;

import javax.swing.JFrame;

import views.GameViewGUI;

public abstract class GameGUI extends JFrame
{
	protected static final long serialVersionUID = 1L;
	
	
	public static GameViewGUI create()
	{
		return new GameViewGUI();
	}
	
	protected GameGUI(String title)
	{
		super(title);
	}
	public abstract void start();
	public abstract void addToConsole(String message);
	
	public abstract void loadHeader();
	public abstract void loadProfile();
	public abstract void reloadProfile();
	public abstract void reloadKaps();
	public abstract void loadMenu();
	public abstract void loadProduction();
	public abstract void reloadProduction();
	public abstract void loadInventory();
	public abstract void reloadInventory();
	public abstract void loadMarket();
	public abstract void reloadMarket();
	public abstract void loadConsole();
}
