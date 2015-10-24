package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Inventory.Inventory.Entry;
import market.Offer;
import market.ProductType;
import production.ProductionBuilding;

public class GameGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	GameController gc;
	JTabbedPane tabPane;
	
	JPanel headerPane;
	JPanel leftPane;
	JPanel profile;
		JLabel kaps;
	JPanel buttons;
	JTabbedPane production;
	JScrollPane marketPane;
	JScrollPane inventory;

	public GameGUI(GameController gc)
	{
		super("KapiSim v0.0.6a");
		this.gc = gc;
		
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
		profile = new JPanel();
		BoxLayout layout = new BoxLayout(profile, BoxLayout.PAGE_AXIS);
		
		JLabel prof = new JLabel("Profile");
		prof.setFont(new Font(prof.getFont().getFontName(), Font.ITALIC, 18));
		JLabel txt = new JLabel("Name: " + gc.getPlayer().getName());
		kaps = new JLabel("Kaps: " + gc.getPlayer().kaps);
		
		profile.add(prof);
		profile.add(txt);
		profile.add(kaps);
		
		
		profile.setLayout(layout);
		//profile.setPreferredSize(new Dimension(150, 100));
		leftPane.add(profile);//, BorderLayout.PAGE_START);
	}
	
	public void reloadProfile()
	{
		reloadKaps();
	}
	
	public void reloadKaps()
	{
		kaps.setText("Kaps: " + gc.getPlayer().kaps);
	}
	
	public void loadButtons()
	{
		buttons = new JPanel();
		BoxLayout layout = new BoxLayout(buttons, BoxLayout.PAGE_AXIS);
		
		JButton bt = new JButton("useless BUTTON");
		buttons.add(bt);
		
		buttons.setLayout(layout);
		//buttons.setPreferredSize(new Dimension(150, 450));
		leftPane.add(buttons);//, BorderLayout.PAGE_END);
	}
	
	public void loadProduction()
	{
		production = new JTabbedPane();
		reloadProduction();
		tabPane.add("Production", production);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void reloadProduction()
	{
		for(final ProductionBuilding pb : gc.getPlayer().productionBuildings)
		{
			JPanel building = new JPanel();
			JButton btProduce = new JButton("produce");
			final JTextField edProduction = new JTextField(10);
			final JComboBox<ProductType> cbProduction = new JComboBox(pb.getPossibleTypes().toArray());
			btProduce.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("YAY");
					ProductType pt = cbProduction.getItemAt(cbProduction.getSelectedIndex());
					int quantity = Integer.parseInt(edProduction.getText());
					pb.produce(pt, 0, quantity);
					reloadInventory();
				}
			});
			building.add(cbProduction);
			building.add(edProduction);
			building.add(btProduce);
			//btProduce.getRootPane().setDefaultButton(btProduce);
			
			production.add(pb.getClass().getSimpleName(),building);
		}
	}

	public void loadInventory()
	{
		inventory = new JScrollPane();
		reloadInventory();
		inventory.validate();
		tabPane.add("Inventory", inventory);
	}
	
	public void reloadInventory()
	{
		String[] columnNames = {
				"Anzahl",
				"Produkt",
				"Qualität"
		};
		ArrayList<Entry> entries = gc.getPlayer().inventory.getWholeInventory();
		Object[][] data = new Object[entries.size()][];
		for(int i = 0; i < entries.size(); i++)
		{
			Entry e = entries.get(i);
			Object[] d = new Object[]
				{
					e.getQuantity(),
					e.getProductType(),
					e.getQuality()
				};
			data[i] = d;
		}
		JTable inventoryTable = new JTable(data, columnNames);
		inventory.getViewport().add(inventoryTable);
		inventory.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				reloadInventory();
			}

			public void focusLost(FocusEvent arg0) {}
		});
		
		reloadKaps();
	}
	
	public void loadMarket()
	{
		marketPane = new JScrollPane();
		reloadMarket();
		tabPane.add("Market", marketPane);
	}
	
	public void reloadMarket()
	{
		ArrayList<Offer> offers = gc.getMarket().getAllOffers();
		
		String[] columns = {"Anzahl",
				"Produkt",
				"Firma",
				"Quali",
				"Preis",
				"Gesamt"};
		
		Object[][] data = new Object[offers.size()][];
		
		for(int i = 0; i < offers.size(); i++)
		{
			Offer o = offers.get(i);
			Object[] d = new Object[]
				{
					o.getQuantity(),
					o.getProduct(),
					o.getOfferer(),
					o.getQuality(),
					o.getCost(),
					o.getTotal()
				};
			data[i] = d;
		}
		
		
		JTable table = new JTable(data, columns);
		marketPane.getViewport().add(table);
		table.setFillsViewportHeight(true);
		tabPane.add("Market", marketPane);
	}
}
