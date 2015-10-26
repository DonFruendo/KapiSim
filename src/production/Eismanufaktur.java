package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Eismanufaktur extends ProductionBuildingController {

	public Eismanufaktur(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Schokoeis);
		possible.add(ProductType.Erdbeereis);
		possible.add(ProductType.Zitroneneis);
		possible.add(ProductType.Orangeneis);
		possible.add(ProductType.Bananeneis);
		possible.add(ProductType.Kaffeeeis);
	}

}
