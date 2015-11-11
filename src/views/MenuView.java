package views;

import interfaces.controller.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import language.Language;

public class MenuView extends JScrollPane {
	private static final long serialVersionUID = -1132848775061633807L;
	private static final Language lang = Game.getController().getLanguagePack();
	
	JPanel mainPanel;
	JButton btPlaceOffer;

	public MenuView()
	{
		super();
	}
	
	private void loadOfferButton()
	{
		btPlaceOffer = new JButton(lang.place_New_Offer);
		btPlaceOffer.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				new PlaceOfferGUI();
			}
			
		});
		this.getViewport().add(btPlaceOffer);
	}
	
	public void loadMenu()
	{
		loadOfferButton();
	}
}
