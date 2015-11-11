package controller;

import java.util.ArrayList;

import language.*;
import interfaces.controller.*;
import interfaces.views.GameGUI;
import production.*;

/**
 * The GameController class contains methods to control the game.
 * It manages all metadata and secures a stable functionality throughout the game. 
 * 
 * @author DonFruendo
 *
 */

public class GameController extends Game
{
	// ** Attributes **
	/**
	 * The only gameController available
	 * <p>
	 * Since there can only be one GameController at a time, the static gameController property 
	 * is declared in the style of a singleton-object.
	 * <p>
	 * See also:<br>
	 * {@link #getController()}
	 */
	private static Game gameController = new GameController();
	/**
	 * The market
	 * <p>
	 * This variable holds the reference to the marketController
	 */
	private Market market;
	
	private ArrayList<Player> allPlayers = new ArrayList<Player>();
	/**
	 * The player
	 * <p>
	 * Currently just holding the reference to the only player-object
	 * but should be changed soon to reference the active player.
	 * <br>
	 * Perhaps deleting it soon to get rid of this rigid declaration..
	 */
	private Player player;
	
	/**
	 * GameGUI
	 * <p>
	 * The gui variable holds the reference to the windows, which is visible to the player.
	 * <p>
	 * See also:<br>
	 * {@link GameViewGUI} 
	 */
	private GameGUI gui;
	
	/**
	 * LanguagePack
	 */
	Language language = new DefaultLanguage();
	
	/**
	 * DebugMode
	 * <p>
	 * Declares how deep the console-outputs go. If there is a debug note and the level is set to "JustErrors",
	 * the message will not be printed.
	 */
	LogState debugMode = LogState.Debug;
	
	// ** Construction **
	/**
	 * The constructor is private due to the concept of singleton-objects.
	 * <p>
	 * A new GameController is created, if and only if there is no GameController-object already existing.
	 */
	private GameController(){}
	
	/**
	 * Creates a new GameController if there is none. Otherwise just returns the existing GameController
	 * @return {@link #gameController}
	 */
	public static Game getController()
	{
		if(gameController == null)
		{
			gameController = new GameController();
		}
		return gameController;
	}
	
	
	// ** Methods **
	/**
	 * Start the game
	 * <p>
	 * Should be called to start the game, obviously.
	 */
	public void startGame()
	{
		market = Market.getController();
		player = new PlayerController(1, 100000);
		allPlayers.add(player);
		Kraftwerk k1 = new Kraftwerk(player);
		Quelle q1 = new Quelle(player);
		Plantage p1 = new Plantage(player);
		Obstplantage o1 = new Obstplantage(player);
		Viehzucht  v1 = new Viehzucht(player);
		Lebensmittelproduktion l1 = new Lebensmittelproduktion(player);
		Getraenkefabrik g1 = new Getraenkefabrik(player);
		Fleischerei f1 = new Fleischerei(player);
		Eismanufaktur e1 = new Eismanufaktur(player);
		player.addProductionBuilding(k1);
		player.addProductionBuilding(q1);
		player.addProductionBuilding(o1);
		player.addProductionBuilding(v1);
		player.addProductionBuilding(p1);
		player.addProductionBuilding(l1);
		player.addProductionBuilding(g1);
		player.addProductionBuilding(f1);
		player.addProductionBuilding(e1);
		
		
		gui = GameGUI.create();
		gui.start();
		market.openMarket();
	}
	
	// ** Player handling **
	public void playerSignUp(Player player)
	{
		allPlayers.add(player);
	}
	
	// ** Console interaction **
	/**
	 * Debug prints the message, if the {@link #debugMode} is Debug or higher.
	 * @param message String to be printed
	 */
	public void Debug(String message)
	{
		if(LogState.Debug.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(language.log_Debug + " > " + message);
		}
	}
	
	/**
	 * Message prints the message, if the {@link #debugMode} is Log or higher.
	 * @param message String to be printed
	 */
	public void message(String message)
	{
		if(LogState.Log.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(message);
		}
	}
	
	/**
	 * Warning prints the message, if the {@link #debugMode} is Warnings or higher.
	 * @param message String to be printed
	 */
	public void Warning(String message)
	{
		if(LogState.Warnings.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(language.log_Warning + " > " + message);
		}
	}
	
	/**
	 * Error prints the message, if the {@link #debugMode} is Errors or higher.
	 * @param message String to be printed
	 */
	public void Error(String message)
	{
		if(LogState.Errors.show(debugMode))
		{
			gui.addToConsole(message);
			System.out.println(language.log_Error + " > " + message);
		}
	}
	// ** Console Interaction end **
	
	/**
	 * 
	 * @return {@link #market}
	 */
	public Market getMarket()
	{
		return market;
	}
	
	/**
	 * @return {@link #player}
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Finds the player with the given ID and returns it
	 * @param ID ID to search by
	 * @return Player with given ID
	 */
	public Player getPlayer(int ID)
	{
		for(Player player : allPlayers)
		{
			if(player.getID() == ID)
				return player;
		}
		return null;
	}
	
	public GameGUI getGameGUI()
	{
		return gui;
	}
	
	public Language getLanguagePack()
	{
		return language;
	}
	
	
	
	
	/** LogState
	 * <p>
	 * The LogState enum declares the importancy of a message. If there is a message with priority "Warning" while
	 * the console outputs everything except "Debug"-messages, it will output. Since the states are ordered this way,
	 * they include every level before themselves. If the console outputs "Warnings", it will also output "Errors".
	 * 
	 * @author DonFruendo
	 *
	 */
	static enum LogState
	{
		Errors,
		Warnings,
		Log,
		Debug;
		
		/**
		 * Decides if the state is ok to show.
		 * <p>
		 * To call this properly, try something like this:
		 * <br>
		 * {@code if(Logstate.Warnings.show(currentLogLevel))}
		 * <br>
		 * Where currentLogLevel should be the current level, you would like to output.
		 * 
		 * @param logState LogLevel, you wish to output
		 * @return true, if {@code logState.ordinal} is greater or equal to {@code this.ordinal}
		 */
		public boolean show(LogState logState)
		{
			return (this.ordinal() <= logState.ordinal());
		}
	}
}
