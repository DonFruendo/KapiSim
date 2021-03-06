package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Lebensmittelproduktion extends ProductionBuildingController {

	public Lebensmittelproduktion(Player player) {
		super(player);
		addPossible(ProductType.Bonbon,			0);
		addPossible(ProductType.Brot,			0);
		addPossible(ProductType.Broetchen,		0);
		addPossible(ProductType.Kaffeepulver,	0);
		addPossible(ProductType.Kakaopulver,	0);
		addPossible(ProductType.Mehl,			0);
		addPossible(ProductType.Schokoriegel,	0);
		addPossible(ProductType.Zucker,			0);
	}

}
