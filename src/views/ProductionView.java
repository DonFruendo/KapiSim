package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import market.ProductType;
import production.ProductionBuildingController;
import controller.GameController;

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
		for(final ProductionBuildingController pb : gc.getPlayer().productionBuildings)
		{
			JPanel building = new JPanel();
			final JComboBox<ProductType> cbProduction = new JComboBox(pb.getPossibleTypes().toArray());
			JButton btProduce = new JButton("produce");
			final JTextField edProduction = new JTextField(10);
			edProduction.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent arg0) {}

				public void keyReleased(KeyEvent arg0) {
					if(arg0.getKeyCode() == 10)
					{
						pressProductionButton(pb, edProduction, cbProduction);
					}
				}
				
				public void keyTyped(KeyEvent arg0) {}
			});
			btProduce.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					pressProductionButton(pb, edProduction, cbProduction);
				}
			});
			building.add(cbProduction);
			building.add(edProduction);
			building.add(btProduce);
			//btProduce.getRootPane().setDefaultButton(btProduce);
			
			this.add(pb.getClass().getSimpleName(),building);
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
