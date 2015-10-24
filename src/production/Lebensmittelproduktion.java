package production;

import market.ProductType;
import game.Player;

public class Lebensmittelproduktion extends ProductionBuilding {

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
