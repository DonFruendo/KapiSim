package production;

import Inventory.Player;
import market.ProductType;

public class Lebensmittelproduktion extends ProductionBuildingController {

	public Lebensmittelproduktion(Player owner) {
		super(owner);
		possible.add(ProductType.Bonbon);
		possible.add(ProductType.Brot);
		possible.add(ProductType.Brötchen);
		possible.add(ProductType.Kaffeepulver);
		possible.add(ProductType.Kakaopulver);
		possible.add(ProductType.Mehl);
		possible.add(ProductType.Schokoriegel);
		possible.add(ProductType.Zucker);
	}

}
