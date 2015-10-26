package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import controller.GameController;

public class GameGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	GameController gc;
	JTabbedPane tabPane;
	
	JPanel headerPane;
	JPanel leftPane;
	ProfileView profile;
	JPanel buttons;
	ProductionView production;
	MarketView marketPane;
	InventoryView inventory;

	public GameGUI()
	{
		super("KapiSim v0.1.0a");
		this.gc = GameController.getGameController();
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		leftPane = new JPanel();
		leftPane.setPreferredSize(new Dimension(150, 600));
		this.add(leftPane, BorderLayout.LINE_START);
		
		// ** Header **
		loadHeader();
		
		// ** Profile **
		loadProfile();
		
		//Spacing
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1,70));
		leftPane.add(p);
		
		// ** Interaction Buttons
		loadButtons();
		
		tabPane = new JTabbedPane();
		
		// ** Production **
		loadProduction();
		
		// ** Inventory **
		loadInventory();
		
		// ** Market **
		loadMarket();
		
		this.add(tabPane, BorderLayout.CENTER);
		this.validate();
		this.pack();
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
	
	public void loadButtons()
	{
		buttons = new JPanel();
		BoxLayout layout = new BoxLayout(buttons, BoxLayout.PAGE_AXIS);
		
		JButton bt = new JButton("useless BUTTON");
		bt.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				new PlaceOfferGUI();
			}
			
		});
		buttons.add(bt);
		
		buttons.setLayout(layout);
		//buttons.setPreferredSize(new Dimension(150, 450));
		leftPane.add(buttons);//, BorderLayout.PAGE_END);
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
}
