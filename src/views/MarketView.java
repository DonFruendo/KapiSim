package views;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MarketView extends JScrollPane {
	private static final long serialVersionUID = 4090992372218060311L;
	
	final GameViewGUI parent;
	
	public MarketView(GameViewGUI parent)
	{
		this.parent = parent;
		this.validate();
	}
	
	public void reloadMarket() {
		JTable table = new JTable(new MarketTableModel());
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		table.getColumn("Kaufen").setCellRenderer(buttonRenderer);
		table.addMouseListener(new JTableButtonMouseListener(this, table));
		this.getViewport().add(table);
		//table.setFillsViewportHeight(true);
	}
	
	
	
	
	private static class JTableButtonRenderer implements TableCellRenderer {        
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            return button;  
        }
    }
	
	private static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;
        private final MarketView parent;

        public JTableButtonMouseListener(MarketView parent, JTable table) {
        	this.parent = parent;
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row    = e.getY()/table.getRowHeight(); //get the row of the button

                    /*Checking the row or column is valid or not*/
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    /*perform a click event*/
                    ((JButton)value).doClick();
                    parent.reloadMarket();
                    parent.parent.reloadInventory();
                }
            }
        }
    }
}
