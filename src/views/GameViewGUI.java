package views;

import interfaces.controller.Game;
import interfaces.views.GameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.GameController;

public class GameViewGUI extends GameGUI
{
	/**
	 * 
	 */
	private static final long serialVersionUID = GameGUI.serialVersionUID;
	Game gc;
	JTabbedPane tabPane;
	
	JPanel headerPane;
	JPanel leftPane;
	ProfileView profile;
	MenuView menu;
	ProductionView production;
	MarketView marketPane;
	InventoryView inventory;
	ConsoleView console;
	
	JPanel bottomPane;

	public GameViewGUI()
	{
		super("KapiSim v0.2.0a");
		this.gc = GameController.getController();
	}
	
	public void start()
	{
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		leftPane = new JPanel();
		leftPane.setPreferredSize(new Dimension(150, 0));
		this.add(leftPane, BorderLayout.LINE_START);
		
		// ** Header **
		loadHeader();
		
		// ** Profile **
		loadProfile();
		
		//Spacing
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1,70));
		leftPane.add(p);
		
		// ** Menu **
		loadMenu();
		
		tabPane = new JTabbedPane();
		
		// ** Production **
		loadProduction();
		
		// ** Inventory **
		loadInventory();
		
		// ** Market **
		loadMarket();
		
		this.add(tabPane, BorderLayout.CENTER);
		
		bottomPane = new JPanel();
		
		// ** Console **
		loadConsole();
		
		this.add(bottomPane, BorderLayout.PAGE_END);
		
		this.validate();
		this.pack();
		this.setVisible(true);
	}
	
	public void loadHeader()
	{
		headerPane = new JPanel();
		JLabel caption = new JLabel("KapiSim Alpha");
		caption.setFont(new Font(caption.getFont().getFontName(), Font.ITALIC, 28));
		headerPane.add(caption);
		headerPane.setPreferredSize(new Dimension(800, 60));
		this.add(headerPane, BorderLayout.PAGE_START);
	}
	
	public void loadProfile()
	{
		profile = new ProfileView(this);
		leftPane.add(profile);//, BorderLayout.PAGE_START);
	}
	
	public void reloadProfile()
	{
		profile.reloadProfile();
	}
	
	public void reloadKaps()
	{
		profile.reloadKaps();
	}
	
	public void loadMenu()
	{
		menu = new MenuView();
		menu.loadMenu();
		leftPane.add(menu);//, BorderLayout.PAGE_END);
	}
	
	public void loadProduction()
	{
		production = new ProductionView(this);
		reloadProduction();
		tabPane.add("Production", production);
	}
	
	public void reloadProduction()
	{
		production.reloadProduction();
	}

	public void loadInventory()
	{
		inventory = new InventoryView(this);
		reloadInventory();
		tabPane.add("Inventory", inventory);
	}
	
	public void reloadInventory()
	{
		inventory.reloadInventory();
	}
	
	public void loadMarket()
	{
		marketPane = new MarketView(this);
		reloadMarket();
		tabPane.add("Market", marketPane);
	}
	
	public void reloadMarket()
	{
		marketPane.reloadMarket();
	}
	
	public void loadConsole()
	{
		console = new ConsoleView();
		console.loadConsole();
		bottomPane.add(console);
	}
	
	public void addToConsole(String message)
	{
		console.addToConsole(message);
		bottomPane.validate();
	}
}
