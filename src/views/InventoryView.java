package views;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.GameController;
import Inventory.Inventory.Entry;

public class InventoryView extends JScrollPane {
	private static final long serialVersionUID = 6460959134763195104L;
	
	GameViewGUI parent;
	
	public InventoryView(GameViewGUI parent)
	{
		this.parent  = parent;
		this.validate();
	}

	public void reloadInventory() {
		String[] columnNames = {
				"Anzahl",
				"Produkt",
				"Qualität"
		};
		ArrayList<Entry> entries = GameController.getController().getPlayer().getWholeInventory();
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
		this.getViewport().add(inventoryTable);
		parent.reloadKaps();
	}
}
