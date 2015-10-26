package game;

import controller.GameController;
import views.GameGUI;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		GameController gc = GameController.getGameController();
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
		
	}

}
