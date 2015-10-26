package market;

import game.Product;

public class Offer
{
	static int nextid = 0;
	
	int id;
	int quantity;
	int quality;
	int cost;
	int total;
	
	int offerer;
	int receiver = -1;
	ProductType product;
	
	
	public Offer(int offererID, ProductType product, int quantity, int quality, int cost)
	{
		this.quantity = quantity;
		this.quality = quality;
		this.cost = cost;
		this.offerer = offererID;
		this.product = product;
		this.total = quantity * cost;
		this.id = nextid;
		nextid++;
	}
	
	public Offer(int offererId, Product product, int quantity, int cost)
	{
		this(offererId, product.type, quantity, product.quality, cost);
	}
	
	public Product getProduct()
	{
		return new Product(product, quality);
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
					&& (o.receiver == receiver)
					&& (o.product  == product)
					&& (o.cost	   == cost)
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

	public int getCost() {
		return cost;
	}

	public int getTotal() {
		return total;
	}

	public int getOffererID() {
		return offerer;
	}

	public int getReceiverID() {
		return receiver;
	}
	
	
}
