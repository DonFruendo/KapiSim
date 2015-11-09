package views;

import interfaces.controller.Game;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Inventory.Inventory.Entry;

public class OfferTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Game gc = Game.getController();
	
	private String[] columnNames;
	private Object[][] data;

	public OfferTableModel()
	{
		
		columnNames = new String[]{
				"Produkt",
				//"Qualität",
				"Anzahl",
				"verkaufen",
				"?"
		};
		ArrayList<Entry> entries = gc.getPlayer().getWholeInventory();
		data = new Object[entries.size()][];
		for(int i = 0; i < data.length; i++)
		{
			Entry entry = entries.get(i);
			
			Object[] row = {
					entry.getProductType(),
					//entry.getQuality(),
					entry.getQuantity(),
					0,
					false
			};
			
			data[i] = row;
		}
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getColumnName(int index)
	{
		return columnNames[index];
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int index)
	{
		return getValueAt(0, index).getClass();
	}
	
	 public boolean isCellEditable(int row, int col)
	 {
		if (columnNames[col].equals("verkaufen") || columnNames[col].equals("?"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	 
	 public void setValueAt(Object value, int row, int col)
	 {
		 data[row][col] = value;
		 fireTableCellUpdated(row, col);
	 }

}
