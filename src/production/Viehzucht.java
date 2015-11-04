package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Viehzucht extends ProductionBuildingController {

	public Viehzucht(Player player) {
		super(player);
		addPossible(ProductType.Milch,	  5);
		addPossible(ProductType.Huhn,	  9);
		addPossible(ProductType.Ei,		  5);
		addPossible(ProductType.Lamm,	405);
		addPossible(ProductType.Rind,	225);
		addPossible(ProductType.Schwein,225);
		//addPossible(ProductType.Wolle,  3);
	}

}
