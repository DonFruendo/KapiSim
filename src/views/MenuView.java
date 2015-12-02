package views;

import interfaces.controller.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.ui.RefineryUtilities;

import language.Language;

public class MenuView extends JScrollPane {
	private static final long serialVersionUID = -1132848775061633807L;
	private static final Language lang = Game.getController().getLanguagePack();
	
	JPanel mainPanel;
	JButton btPlaceOffer;
	JButton btStatistics;

	public MenuView()
	{
		super();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	}
	
	private void loadOfferButton()
	{
		btPlaceOffer = new JButton(lang.place_New_Offer);
		btPlaceOffer.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				new PlaceOfferGUI();
			}
			
		});
		mainPanel.add(btPlaceOffer);
	}
	
	private void loadStatisticsButton()
	{
		btStatistics = new JButton("lang.statistics");
		btStatistics.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				GraphGUI graph = new GraphGUI("lang.statistics");
				RefineryUtilities.centerFrameOnScreen(graph);
				graph.setVisible(true);
			}
			
		});
		mainPanel.add(btStatistics);
	}
	
	public void loadMenu()
	{
		loadOfferButton();
		loadStatisticsButton();
		this.getViewport().add(mainPanel);
	}
}
