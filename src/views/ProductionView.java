package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
			
			final JComboBox<ProductType> cbProduction = new JComboBox(prodBuilding.getPossibleTypes().toArray());
			JButton btProduce = new JButton("produce");
			final JTextField edProduction = new JTextField(10);
			
			edProduction.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent arg0) {}

				public void keyReleased(KeyEvent arg0) {
					if(arg0.getKeyCode() == 10)
					{
						pressProductionButton(prodBuilding, edProduction, cbProduction);
					}
				}
				
				public void keyTyped(KeyEvent arg0) {}
			});
			btProduce.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					pressProductionButton(prodBuilding, edProduction, cbProduction);
				}
			});
			
			// Table
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
					prodBuilding.getProductionCost(possibleType)
				};
				data[i] = row;
			}
			JTable dependencyTable = new JTable(data, columnNames);
			
			buildingPanelTop.add(cbProduction);
			buildingPanelTop.add(edProduction);
			buildingPanelTop.add(btProduce);
			buildingPanelBottom.add(dependencyTable);
			
			buildingPanel.setLayout(new BoxLayout(buildingPanel, BoxLayout.PAGE_AXIS));
			buildingPanel.add(buildingPanelTop);
			buildingPanel.add(buildingPanelBottom);
			
			this.add(prodBuilding.getClass().getSimpleName(),buildingPanel);
		}
	}
	
	private void pressProductionButton(final ProductionBuildingController pb,
			final JTextField edProduction,
			final JComboBox<ProductType> cbProduction) {
		gc.Debug("YAY");
		ProductType pt = cbProduction.getItemAt(cbProduction.getSelectedIndex());
		int quantity = Integer.parseInt(edProduction.getText());
		edProduction.setText("");
		pb.produce(pt, 0, quantity);
		parent.reloadInventory();
	}
}
