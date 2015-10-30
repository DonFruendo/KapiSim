package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Eismanufaktur extends ProductionBuildingController {

	public Eismanufaktur(PlayerController owner) {
		super(owner);
		addPossible(ProductType.Schokoeis,	15);
		addPossible(ProductType.Erdbeereis,	15);
		addPossible(ProductType.Zitroneneis,15);
		addPossible(ProductType.Orangeneis,	15);
		addPossible(ProductType.Bananeneis,	15);
		addPossible(ProductType.Kaffeeeis,	15);
	}

}
