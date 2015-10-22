package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Inventory.Inventory.Entry;
import market.Offer;
import market.ProductType;
import production.ProductionBuilding;

public class GameGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	GameController gc;
	JTabbedPane tabPane;
	JScrollPane marketPane;
	
	JScrollPane inventory;

	public GameGUI(GameController gc)
	{
		super("KapiSim v0.0.6a");
		this.gc = gc;
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		JPanel profile = new JPanel();
		JLabel txt = new JLabel("Hallo");
		profile.add(txt);
		this.add(profile);
		
		tabPane = new JTabbedPane();
		
		// ** Production **
		reloadProduction();
		
		
		// ** Inventory **
		loadInventory();
		
		// ** Market **
		loadMarket();
		
		this.add(tabPane);
		this.validate();
	}
	
	public void reloadProduction()
	{
		JTabbedPane production = new JTabbedPane();
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
		tabPane.add("Production", production);
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
