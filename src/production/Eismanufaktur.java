package production;

import Inventory.Player;
import market.ProductType;

public class Eismanufaktur extends ProductionBuildingController {

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
