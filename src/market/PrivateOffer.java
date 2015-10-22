package market;

import game.Player;
import game.Product;

public class PrivateOffer extends Offer
{

	public PrivateOffer(Player offerer, Player receiver, Product product, int quantity,
			double cost) {
		this(offerer, receiver, product.type, quantity,product.quality, cost);
	}
	
	public PrivateOffer(Player offerer, Player receiver, ProductType product, int quantity, int quality,	double cost) {
		super(offerer, product, quantity, quality, cost);
		this.receiver = receiver;
	}
}
