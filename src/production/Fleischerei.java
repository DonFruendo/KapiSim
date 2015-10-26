package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Fleischerei extends ProductionBuildingController {

	public Fleischerei(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Hackfleisch);
		possible.add(ProductType.Lammfleisch);
		possible.add(ProductType.Rindfleisch);
		possible.add(ProductType.Schweinefleisch);
		possible.add(ProductType.Tierfutter);
		possible.add(ProductType.Wurst);
	}

}
