package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlaceOfferGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public PlaceOfferGUI()
	{
		super("Offer");
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel headerCaption = new JLabel("Place new Offer");
		
		
		this.add(headerCaption);
		this.pack();
	}
}
