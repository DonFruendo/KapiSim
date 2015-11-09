package views;

import game.Product;
import interfaces.controller.Game;
import interfaces.controller.Market;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import market.Offer;
import market.ProductType;

public class MarketTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Game gc = Game.getController();
	private static final Market market = Market.getController();
	
	private String[] columns;
	private Object[][] data;

	public MarketTableModel() {
		ArrayList<Offer> offers = Game.getController().getMarket().getAllOffers();
		
		columns = new String[]{
				"Anzahl",
				"Produkt",
				"Firma",
				"Quali",
				"Preis/Stk.",
				"Gesamt",
				"Kaufen"
				};
		
		data = new Object[offers.size()][];
		
		for(int i = 0; i < offers.size(); i++)
		{
			Offer o = offers.get(i);
			Object[] d = new Object[]
				{
					o.getQuantity(),
					o.getProduct(),
					Game.getController().getPlayer(o.getOffererID()),
					o.getQuality(),
					o.getCost(),
					o.getTotal(),
					new JButton("Kaufen")
				};
			data[i] = d;
		}
		
		
		//TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        //table.getColumn("Button1").setCellRenderer(buttonRenderer);
	}

	public Object getValueAt(final int rowIndex, final int columnIndex) 
	{
		switch (columnIndex) 
		{
		// 0 = Anzahl
		case 0: return (Integer) data[rowIndex][columnIndex];
		// 1 = Produkt
		case 1: // fall through
		// 2 = Firma
		case 2: return data[rowIndex][columnIndex];
		// 3 = Quali
		case 3: return (Integer) data[rowIndex][columnIndex];
		// 4 = Preis/Stk.
		case 4: // fall through
		// 5 = Gesamt
		case 5: return (Double) (((Integer) data[rowIndex][columnIndex]) / 100.);
		// 6 = Kaufen
		case 6: final JButton buyButton = new JButton(columns[columnIndex]);
			buyButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						Offer thatOffer = new Offer(
								gc.getPlayer().getID(), 
								((Product)(data[rowIndex][1])).type, 
								(Integer)data[rowIndex][0], 
								(Integer)data[rowIndex][3], 
								(Integer)data[rowIndex][4]
								);
						market.takeOffer(gc.getPlayer(), thatOffer); // TODO ID????
						gc.Debug(thatOffer.toString());
						
					}
				});
			return buyButton;
		default: return "Error";
		}
	}

	public int getColumnCount() {
		return columns.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int index)
	{
		return columns[index];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		switch(columnIndex)
		{
		case 6: return false;
		
		default: return false;
		}
	}
}
