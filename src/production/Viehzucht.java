package production;

import controller.PlayerController;
import market.ProductType;

public class Viehzucht extends ProductionBuildingController {

	public Viehzucht(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Ei);
		possible.add(ProductType.Huhn);
		possible.add(ProductType.Lamm);
		possible.add(ProductType.Milch);
		possible.add(ProductType.Rind);
		possible.add(ProductType.Schwein);
		//possible.add(ProductType.Wolle);
	}

}
