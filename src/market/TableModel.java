package market;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.MarketController;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {
	MarketController market;
	String[] columns = {"Anzahl",
			"Produkt",
			"Firma",
			"Quali",
			"Preis",
			"Gesamt"};
	
	ArrayList<Offer> offers;
	Object[][] data;
	
	public TableModel(MarketController market)
	{
		this.market = market;
		offers = market.getAllOffers();
		data = new Object[offers.size()][];
	}
	
	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
