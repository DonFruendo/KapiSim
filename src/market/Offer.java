package market;

import game.Player;
import game.Product;

public class Offer
{
	static int nextid = 0;
	
	int id;
	int quantity;
	int quality;
	double cost;
	double total;
	
	Player offerer;
	Player receiver = null;
	ProductType product;
	
	
	public Offer(Player offerer, ProductType product, int quantity, int quality, double cost)
	{
		this.quantity = quantity;
		this.quality = quality;
		this.cost = cost;
		this.offerer = offerer;
		this.product = product;
		this.total = quantity * cost;
		this.id = nextid;
		nextid++;
	}
	
	public Offer(Player offerer, Product product, int quantity, double cost)
	{
		this(offerer, product.type, quantity, product.quality, cost);
	}
	
	public Product getProduct()
	{
		Product p = new Product();
		p.type = product;
		p.quality = quality;
		return p;
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Offer))
		{
			return false;
		}
		else
		{
			Offer o = (Offer) obj;
			return (   (o.offerer  == offerer)
					&& (o.product  == product)
					&& (o.cost     == cost)
					&& (o.quality  == quality)
					&& (o.quantity == quantity));
		}
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getQuality() {
		return quality;
	}

	public double getCost() {
		return cost;
	}

	public double getTotal() {
		return total;
	}

	public Player getOfferer() {
		return offerer;
	}

	public Player getReceiver() {
		return receiver;
	}
	
	
}
