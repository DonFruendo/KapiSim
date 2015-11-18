package controller;

import game.Product;
import interfaces.controller.Game;
import interfaces.controller.Player;
import interfaces.controller.ProductionBuilding;

import java.util.ArrayList;

import language.Language;
import market.Offer;
import market.ProductType;
import Inventory.Inventory;
import Inventory.Inventory.Entry;

/**
 * The PlayerController holds the methods to identify and alter the stats of a player
 * <p>
 * Methods: <br>
 * {@link #addToInventory(Product, int)}
 * @author DonFruendo
 *
 */
public class PlayerController implements Player
{
	/**
	 * Currently used to give the player a random name
	 */
	private static String[] allNames = {"Perry", "Hugo", "Fritz", "Aaron", "Herbert", "Eliza"};
	/**
	 * LanguagePack
	 */
	protected static final Language lang = Game.getController().getLanguagePack();
	/**
	 * The ID
	 * <p>
	 * This variable holds the last ID, which was given to any player
	 */
	private static int lastID = 0;
	/**
	 * The name of the player-object
	 */
	protected String name;
	/** 
	 * The unique playerID
	 */
	private int id;
	/**
	 * The inventory of the player
	 * See also:
	 * {@link Inventory}
	 */
	protected Inventory inventory;
	/**
	 * The current Kapital of the player.
	 * <p>
	 * The currency is saved in int-format to prevent loss of accuracy. To get the real amount of Kaps, divide by 100. 
	 */
	private int kaps;
	/**
	 * List contains every building, the player posseses
	 */
	public ArrayList<ProductionBuilding> productionBuildings; // TODO public ist doof
	/**
	 * Reference to the GameController
	 */
	protected final Game gc = Game.getController();
	
	/**
	 * The Constructor
	 * Creates a new player with given ID and Kapital
	 * @param id - the ID, the player gets
	 * @param kapital - the money he receives as a startup
	 */
	public PlayerController()
	{
		this.id = ++lastID;
		int r = (int)(Math.random() * allNames.length);
		this.name = allNames[r];
		inventory = new Inventory();
		productionBuildings = new ArrayList<ProductionBuilding>();
		this.kaps = gc.getStartingKaps();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}


	// ** Financial Stuff **
	/**
	 * Check if the player has enough kapital
	 * @param kapital to be checked against
	 * @return true if the player has equal or more kaps
	 */
	public boolean hasKapital(int kapital)
	{
		return (kaps >= kapital);
	}
	
	/**
	 * @return the kaps
	 */
	public int getKaps()
	{
		return kaps;
	}
	
	/**
	 * Reduces {@code this.kaps} by {@code kapital}
	 * @param kapital
	 */
	public void pay(int kapital)
	{
		kaps -= kapital;
	}
	
	/** 
	 * Adds {@code kapital} to {@code this.kaps}
	 * @param kapital
	 */
	public void getPaid(int kapital)
	{
		kaps += kapital;
	}
	
	
	public String toString()
	{
		return this.name + " [id=" + this.id + "]";
	}

	/**
	 * See also:
	 * {@link Inventory.Inventory}
	 * @return the inventory
	 */
	public ArrayList<Entry> getWholeInventory() {
		return inventory.getWholeInventory();
	}

	/**
	 * See also:
	 * {@link Inventory.Inventory}
	 * @param tmP
	 * @param tmQ
	 * @return true, if player has enough in stock
	 */
	public boolean validateStock(Product tmP, int tmQ) {
		return inventory.validateStock(tmP, tmQ);
	}

	/**
	 * See also:
	 * {@link Inventory.Inventory}
	 * @param entries
	 */
	public void removeFromInventory(ArrayList<Entry> entries) {
		inventory.removeFromInventory(entries);
	}

	/**
	 * @return the {@link #inventory}
	 */
	public Inventory getInventory()
	{
		return inventory.getCopy();
	}

	/**
	 * See also: {@link Inventory.Inventory}
	 * @param p
	 * @param quantity
	 */
	public void addToInventory(Product p, int quantity) {
		inventory.addToInventory(p, quantity);
	}
	
	/**
	 * See also:
	 * {@link Inventory.Inventory}
	 * @param product
	 * @param quantity
	 */
	public void removeFromInventory(Product product, int quantity) {
		inventory.removeFromInventory(product, quantity);
	}
	
	public void offerWasTaken(Offer offer)
	{
		gc.message(offer + " " + lang.was_taken);
	}
	
	
	public ArrayList<ProductType> getAllProducts()
	{
		return inventory.getAllProducts();
	}

	public void addProductionBuilding(ProductionBuilding productionBuilding)
	{		
		productionBuildings.add(productionBuilding);
	}

	public ArrayList<ProductionBuilding> getProductionBuildings()
	{
		// TODO
		return productionBuildings;
	}
}
