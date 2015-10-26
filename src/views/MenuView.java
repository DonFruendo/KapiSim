package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuView extends JScrollPane {
	private static final long serialVersionUID = -1132848775061633807L;
	
	JPanel mainPanel;
	JButton btPlaceOffer;

	public MenuView()
	{
		super();
	}
	
	private void loadOfferButton()
	{
		btPlaceOffer = new JButton("Place New Offer");
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
