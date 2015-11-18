package views;

import interfaces.controller.Game;
import interfaces.controller.Market;
import interfaces.views.OfferGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import language.Language;
import market.ProductType;

public class PlaceOfferGUI extends OfferGUI {
	private static final long serialVersionUID = OfferGUI.serialVersionUID;
	private static final Market market = Market.getController();
	private static final Language lang = Game.getController().getLanguagePack();
	private final JPanel mainPanel = new JPanel();
	
	public PlaceOfferGUI()
	{
		super(lang.offer);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Caption
		JLabel headerCaption = new JLabel(lang.place_New_Offer);
		
		// Table
		JScrollPane tableContainer = new JScrollPane();
		final JTable table = new JTable(new OfferTableModel());
		tableContainer.getViewport().add(table);
		table.setFillsViewportHeight(true);
		
		//Buttonsssaaaa
		JButton submit = new JButton(lang.submit);
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				pressSubmitButton(table);
			}
		});
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(headerCaption);
		mainPanel.add(tableContainer);
		mainPanel.add(submit);
		
		this.add(mainPanel);
		this.pack();
	}
	
	private void pressSubmitButton(JTable table)
	{
		boolean check = false;
		for(int i = 0; i < table.getRowCount(); i++)
		{
			if((Boolean)table.getValueAt(i, table.getColumnCount() -1))
			{
				market.registerOffer(Market.createOffer(
						(ProductType)table.getValueAt(i, 0), 
						(Integer)table.getValueAt(i, 2), 
						(Integer)table.getValueAt(i, 3)
						));
				check = true;
			}
		}
		if(check)
		{
			this.dispose();
		}
	}
}
