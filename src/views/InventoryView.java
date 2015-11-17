package views;

import interfaces.controller.Game;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import language.Language;
import Inventory.Inventory.Entry;

public class InventoryView extends JScrollPane {
	private static final long serialVersionUID = 6460959134763195104L;
	
	GameViewGUI parent;
	Language lang = Game.getController().getLanguagePack();
	
	public InventoryView(GameViewGUI parent)
	{
		this.parent  = parent;
		this.validate();
	}

	public void reloadInventory() {
		String[] columnNames = {
				lang.quantity,
				lang.product,
				lang.quality
		};
		ArrayList<Entry> entries = Game.getController().getPlayer().getWholeInventory();
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
