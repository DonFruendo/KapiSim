package game;

import controller.GameController;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		GameController gc = GameController.getGameController();
		gc.startGame();
		
	}

}
