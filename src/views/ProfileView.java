package views;

import interfaces.controller.Game;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import language.Language;

public class ProfileView extends JPanel {
	private static final long serialVersionUID = -1866964111398088456L;
	private static final Language lang = Game.getController().getLanguagePack();
	
	GameViewGUI parent;
	JLabel kaps;
	
	public ProfileView(GameViewGUI parent)
	{
		this.parent = parent;
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		
		JLabel prof = new JLabel(lang.profile);
		prof.setFont(new Font(prof.getFont().getFontName(), Font.ITALIC, 18));
		JLabel txt = new JLabel(lang.name + ": " + Game.getController().getPlayer().getName());
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
		kaps.setText(lang.kaps + ": " + (Game.getController().getPlayer().getKaps() / 100.));
	}

	public void reloadProfile()
	{
		reloadKaps();
	}
}
