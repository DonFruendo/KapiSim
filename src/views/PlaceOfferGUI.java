package views;

import interfaces.controller.Game;
import interfaces.views.OfferGUI;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PlaceOfferGUI extends OfferGUI {
	private static final long serialVersionUID = OfferGUI.serialVersionUID;
	private static final Game gc = Game.getController();
	
	private final JPanel mainPanel = new JPanel();
	
	public PlaceOfferGUI()
	{
		super("Offer");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel headerCaption = new JLabel("Place new Offer");
		
		JScrollPane tableContainer = new JScrollPane();
		JTable table = new JTable(new OfferTableModel());
		tableContainer.getViewport().add(table);
		table.setFillsViewportHeight(true);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(headerCaption);
		mainPanel.add(tableContainer);
		
		this.add(mainPanel);
		this.pack();
	}
}
