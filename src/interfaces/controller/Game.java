package interfaces.controller;

import language.Language;
import interfaces.views.*;
import controller.GameController;

public abstract class Game
{
	public static final long serialVersionAPI = 1L;
	
	public static Game getController()
	{
		return GameController.getController();
	}
	public abstract void startGame();
	public abstract int getStartingKaps();
	public abstract void playerSignUp(Player player);
	public abstract void Debug(String message);
	public abstract void message(String message);
	public abstract void Warning(String message);
	public abstract void Error(String message);
	public abstract Market getMarket();
	public abstract Player getPlayer();
	public abstract Player getPlayer(int ID);
	public abstract GameGUI getGameGUI();
	public abstract Language getLanguagePack();
}
