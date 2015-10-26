package views;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

public class ProfileView extends JPanel {
	private static final long serialVersionUID = -1866964111398088456L;
	
	GameGUI parent;
	JLabel kaps;
	
	public ProfileView(GameGUI parent)
	{
		this.parent = parent;
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		
		JLabel prof = new JLabel("Profile");
		prof.setFont(new Font(prof.getFont().getFontName(), Font.ITALIC, 18));
		JLabel txt = new JLabel("Name: " + GameController.getGameController().getPlayer().getName());
		kaps = new JLabel();
		reloadKaps();
		
		this.add(prof);
		this.add(txt);
		this.add(kaps);
		
		
		this.setLayout(layout);
		//this.setPreferredSize(new Dimension(150, 100));
	}
	
	public void reloadKaps()
	{
		kaps.setText("Kaps: " + (GameController.getGameController().getPlayer().getKaps() / 100.));
	}

	public void reloadProfile()
	{
		reloadKaps();
	}
}
