package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Fleischerei extends ProductionBuildingController {

	public Fleischerei(PlayerController owner) {
		super(owner);
		addPossible(ProductType.Hackfleisch,	0);
		addPossible(ProductType.Lammfleisch,	0);
		addPossible(ProductType.Rindfleisch,	0);
		addPossible(ProductType.Schweinefleisch,0);
		addPossible(ProductType.Tierfutter,		0);
		addPossible(ProductType.Wurst,			0);
	}

}
