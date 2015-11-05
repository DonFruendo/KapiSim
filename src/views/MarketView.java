package views;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.GameController;
import market.Offer;

public class MarketView extends JScrollPane {
	private static final long serialVersionUID = 4090992372218060311L;
	
	GameGUI parent;
	
	public MarketView(GameGUI parent)
	{
		this.parent = parent;
		this.validate();
	}
	
	public void reloadMarket() {
		ArrayList<Offer> offers = GameController.getController().getMarket().getAllOffers();
		
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
					GameController.getController().getPlayer(o.getOffererID()),
					o.getQuality(),
					o.getCost(),
					o.getTotal()
				};
			data[i] = d;
		}
		
		
		JTable table = new JTable(data, columns);
		this.getViewport().add(table);
		//table.setFillsViewportHeight(true);
	}

}
