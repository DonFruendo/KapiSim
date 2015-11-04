package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Obstplantage extends ProductionBuildingController {

	public Obstplantage(Player player) {
		super(player);
		addPossible(ProductType.Apfel,		5);
		addPossible(ProductType.Banane,		5);
		addPossible(ProductType.Birne,		5);
		addPossible(ProductType.Erdbeere,	5);
		addPossible(ProductType.Orange,		5);
		addPossible(ProductType.Weintraube,	5);
		addPossible(ProductType.Zitrone,	5);
	}

}
