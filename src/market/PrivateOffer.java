package market;

import game.Product;

public class PrivateOffer extends Offer
{

	public PrivateOffer(int offererID, int receiverID, Product product, int quantity,
			int cost) {
		this(offererID, receiverID, product.type, quantity,product.quality, cost);
	}
	
	public PrivateOffer(int offererID, int receiverID, ProductType product, int quantity, int quality,	int cost) {
		super(offererID, product, quantity, quality, cost);
		this.receiver = receiverID;
	}
}
