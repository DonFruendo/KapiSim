package views;

import interfaces.controller.ProductionBuilding;
import interfaces.controller.Game;
//import controller.GameController;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import market.ProductType;
import market.ProductType.Dp;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductionView extends JTabbedPane {
	private static final long serialVersionUID = -6160980084989311357L;
	
	GameViewGUI parent;
	Game gc;

	public ProductionView(GameViewGUI parent)
	{
		super();
		this.parent = parent;
		this.gc = Game.getController();
	}
	
	public void reloadProduction()
	{
		for(final ProductionBuilding prodBuilding : gc.getPlayer().getProductionBuildings())
		{
			// ** main Panels **
			JPanel buildingPanel = new JPanel();
			JPanel buildingPanelTop = new JPanel();
			JPanel buildingPanelBottom = new JPanel();
			
			// ** ComboBox Auswahl **
			final JComboBox<ProductType> cbProduction = new JComboBox(prodBuilding.getPossibleTypes().toArray());
			
			// ** Button produce **
			final JButton btProduce = new JButton("produce");
			
			// ** Label Quantity **
			final JLabel labelCost = new JLabel("0.0 kaps"); 
			
			// ** TextField anzahl **
			final JTextField textFieldAnzahl = new JTextField(10);
			
			// ** DependencyTable **
			JTable dependencyTable;
			String[] columnNames = {"Produkt", "Braucht", "Kostet"};
			Object[][] data = new Object[prodBuilding.getPossibleTypes().size()][];
			for(int i = 0; i < prodBuilding.getPossibleTypes().size(); i++)
			{
				ProductType possibleType = prodBuilding.getPossibleTypes().get(i);
				ArrayList<String> dependencies = new ArrayList<String>();
				for(Dp dependency : possibleType.getDependencies())
				{
					dependencies.add(dependency.toString());
				}
				Object[] row = new Object[]{
					possibleType,
					dependencies,
					prodBuilding.getProductionCost(possibleType) / 100.
				};
				data[i] = row;
			}
			dependencyTable = new JTable(data, columnNames);
			
			// ** Container **
			JScrollPane dependencyTableContainer = new JScrollPane(dependencyTable);
			dependencyTable.setFillsViewportHeight(true);
			
			// ** ActionListeners **
			btProduce.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					pressProductionButton(prodBuilding, textFieldAnzahl, cbProduction);
				}
			});
		
			textFieldAnzahl.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent arg0) {}

				public void keyReleased(KeyEvent arg0) {
					if(arg0.getKeyCode() == 10)
					{
						pressProductionButton(prodBuilding, textFieldAnzahl, cbProduction);
					}
					updateCost(prodBuilding, textFieldAnzahl, labelCost, cbProduction);
				}
				
				public void keyTyped(KeyEvent arg0)
				{
				}
			});
			
			
			// ** Resize **
			dependencyTableContainer.setPreferredSize(new Dimension(500, 300));
			buildingPanelTop.setPreferredSize(new Dimension(500, 50));
			TableColumn column = null;
			for (int i = 0; i < dependencyTable.getColumnCount(); i++) {
			    column = dependencyTable.getColumnModel().getColumn(i);
			    if (i == 2) {
			        column.setPreferredWidth(500);
			    } else {
			        column.setPreferredWidth(2000);
			    }
			}
			
			
			// ** AddToPanel **
			buildingPanel.setLayout(new BoxLayout(buildingPanel, BoxLayout.PAGE_AXIS));
			buildingPanel.add(buildingPanelTop);
			buildingPanel.add(buildingPanelBottom);
			
			buildingPanelTop.add(cbProduction);
			buildingPanelTop.add(textFieldAnzahl);
			buildingPanelTop.add(btProduce);
			buildingPanelTop.add(labelCost);
			buildingPanelBottom.add(dependencyTableContainer);
			
			this.add(prodBuilding.getClass().getSimpleName(),buildingPanel);
		}
	}
	
	private void pressProductionButton(final ProductionBuilding pb,
			final JTextField textFieldAnzahl,
			final JComboBox<ProductType> cbProduction) {
		gc.Debug("YAY");
		ProductType pt = cbProduction.getItemAt(cbProduction.getSelectedIndex());
		int quantity = Integer.parseInt(textFieldAnzahl.getText());
		textFieldAnzahl.setText("");
		pb.produce(pt, 0, quantity);
		parent.reloadInventory();
	}
	
	private void updateCost(ProductionBuilding prodBuilding,
			JTextField textfield, 
			JLabel labelCost, 
			JComboBox<ProductType> cbProduction)
	{
		long quantity = 0;
		try {
			quantity = Long.parseLong(textfield.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int cost = prodBuilding.getProductionCost(cbProduction.getItemAt(cbProduction.getSelectedIndex()));
		
		labelCost.setText((quantity * cost) / 100. + " kaps");
	}
}
