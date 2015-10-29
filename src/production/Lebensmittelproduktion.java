package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Lebensmittelproduktion extends ProductionBuildingController {

	public Lebensmittelproduktion(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Bonbon);
		possible.add(ProductType.Brot);
		possible.add(ProductType.Broetchen);
		possible.add(ProductType.Kaffeepulver);
		possible.add(ProductType.Kakaopulver);
		possible.add(ProductType.Mehl);
		possible.add(ProductType.Schokoriegel);
		possible.add(ProductType.Zucker);
	}

}
