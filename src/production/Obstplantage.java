package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Obstplantage extends ProductionBuildingController {

	public Obstplantage(PlayerController owner) {
		super(owner);
		addPossible(ProductType.Apfel,		5);
		addPossible(ProductType.Banane,		5);
		addPossible(ProductType.Birne,		5);
		addPossible(ProductType.Erdbeere,	5);
		addPossible(ProductType.Orange,		5);
		addPossible(ProductType.Weintraube,	5);
		addPossible(ProductType.Zitrone,	5);
	}

}
