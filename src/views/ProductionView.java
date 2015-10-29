package views;

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
import controller.GameController;
import controller.ProductionBuildingController;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductionView extends JTabbedPane {
	private static final long serialVersionUID = -6160980084989311357L;
	
	GameGUI parent;
	GameController gc;

	public ProductionView(GameGUI parent)
	{
		super();
		this.parent = parent;
		this.gc = GameController.getGameController();
	}
	
	public void reloadProduction()
	{
		for(final ProductionBuildingController prodBuilding : gc.getPlayer().productionBuildings)
		{
			JPanel buildingPanel = new JPanel();
			JPanel buildingPanelTop = new JPanel();
			JPanel buildingPanelBottom = new JPanel();
			
			// ** ComboBox Auswahl **
			final JComboBox<ProductType> cbProduction = new JComboBox(prodBuilding.getPossibleTypes().toArray());
			
			// ** Button produce **
			final JButton btProduce = new JButton("produce");
			
			// ** Label Quantity **
			final JLabel labelQuantity = new JLabel("0.0 kaps"); 
			
			// ** TextField anzahl **
			final JTextField textFieldAnzahl = new JTextField(10);
			
			// ** DependencyTable **
			String[] columnNames = {"Produkt", "Braucht", "Kostet"};
			Object[][] data = new Object[prodBuilding.getPossibleTypes().size()][];
			for(int i = 0; i < prodBuilding.getPossibleTypes().size(); i++)
			{
				ProductType possibleType = prodBuilding.getPossibleTypes().get(i);
				ArrayList<ProductType> dependencies = new ArrayList<ProductType>();
				for(Dp dependency : possibleType.getDependencies())
				{
					dependencies.add(dependency.getType());
				}
				Object[] row = new Object[]{
					possibleType,
					dependencies,
					ProductionBuildingController.getProductionCost(possibleType) / 100.
				};
				data[i] = row;
			}
			JTable dependencyTable = new JTable(data, columnNames);
			TableColumn column = null;
			for (int i = 0; i < dependencyTable.getColumnCount(); i++) {
			    column = dependencyTable.getColumnModel().getColumn(i);
			    if (i == 2) {
			        column.setPreferredWidth(500);
			    } else {
			        column.setPreferredWidth(2000);
			    }
			}
			
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
					updateQuantity(textFieldAnzahl, labelQuantity, cbProduction);
				}
				
				public void keyTyped(KeyEvent arg0)
				{
				}
			});
			
			
			// ** Container **
			JScrollPane dependencyTableContainer = new JScrollPane(dependencyTable);
			dependencyTable.setFillsViewportHeight(true);
			
			
			// ** AddToPanel **
			buildingPanelTop.add(cbProduction);
			buildingPanelTop.add(textFieldAnzahl);
			buildingPanelTop.add(btProduce);
			buildingPanelTop.add(labelQuantity);
			buildingPanelBottom.add(dependencyTableContainer);
			
			buildingPanel.setLayout(new BoxLayout(buildingPanel, BoxLayout.PAGE_AXIS));
			buildingPanel.add(buildingPanelTop);
			buildingPanel.add(buildingPanelBottom);
			
			this.add(prodBuilding.getClass().getSimpleName(),buildingPanel);
		}
	}
	
	private void pressProductionButton(final ProductionBuildingController pb,
			final JTextField textFieldAnzahl,
			final JComboBox<ProductType> cbProduction) {
		gc.Debug("YAY");
		ProductType pt = cbProduction.getItemAt(cbProduction.getSelectedIndex());
		int quantity = Integer.parseInt(textFieldAnzahl.getText());
		textFieldAnzahl.setText("");
		pb.produce(pt, 0, quantity);
		parent.reloadInventory();
	}
	
	private void updateQuantity(JTextField textfield, JLabel labelQuantity, JComboBox<ProductType> cbProduction)
	{
		long quantity = 0;
		try {
			quantity = Long.parseLong(textfield.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int cost = ProductionBuildingController.getProductionCost(cbProduction.getItemAt(cbProduction.getSelectedIndex()));
		
		labelQuantity.setText((quantity * cost) / 100. + " kaps");
	}
}
