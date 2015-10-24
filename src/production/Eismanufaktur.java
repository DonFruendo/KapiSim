package production;

import market.ProductType;
import game.Player;

public class Eismanufaktur extends ProductionBuilding {

	public Eismanufaktur(Player owner) {
		super(owner);
		possible.add(ProductType.Schokoeis);
		possible.add(ProductType.Erdbeereis);
		possible.add(ProductType.Zitroneneis);
		possible.add(ProductType.Orangeneis);
		possible.add(ProductType.Bananeneis);
		possible.add(ProductType.Kaffeeeis);
	}

}
