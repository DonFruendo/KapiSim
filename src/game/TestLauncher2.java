package game;

import interfaces.controller.Game;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		Game gc = Game.getController();
		gc.startGame();
		
	}

}
