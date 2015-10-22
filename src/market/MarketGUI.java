package market;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MarketGUI
{
	Market market;
	
	public MarketGUI(Market market)
	{
		this.market = market;
	}
	
	public void display()
	{
		System.out.println("T  Product");
		for(ProductType p: ProductType.values())
		{
			System.out.println(p.getTier() + "  " + p);
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
					o.quantity,
					o.product,
					o.offerer,
					o.quality,
					o.cost,
					o.total
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
