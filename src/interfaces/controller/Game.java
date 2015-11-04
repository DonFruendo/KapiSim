package interfaces.controller;

public interface Game
{
	public void startGame();
	public void Debug(String message);
	public void message(String message);
	public void Warning(String message);
	public void Error(String message);
	public Market getMarket();
	public Player getPlayer();
	public Player getPlayer(int ID);
}
