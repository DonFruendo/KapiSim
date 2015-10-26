package views;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.GameController;
import controller.MarketController;
import market.Offer;
import market.ProductType;

public class MarketGUI
{
	MarketController market;
	GameController gc;
	
	public MarketGUI(MarketController market, GameController gc)
	{
		this.market = market;
		this.gc = gc;
	}
	
	public void display()
	{
		gc.Debug("T  Product");
		for(ProductType p: ProductType.values())
		{
			gc.Debug(p.getTier() + "  " + p);
		}
		
		
		ArrayList<Offer> offers = market.getAllOffers();
		
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
					gc.getPlayer(o.getOffererID()),
					o.getQuality(),
					o.getCost(),
					o.getTotal()
				};
			data[i] = d;
		}
		
		
		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		JFrame frame = new JFrame("Marktübersicht");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(scrollPane);
	}
}
