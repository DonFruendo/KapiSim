package production;

import Inventory.Player;
import market.ProductType;

public class Obstplantage extends ProductionBuildingController {

	public Obstplantage(Player owner) {
		super(owner);
		possible.add(ProductType.Apfel);
		possible.add(ProductType.Banane);
		possible.add(ProductType.Birne);
		possible.add(ProductType.Erdbeere);
		possible.add(ProductType.Orange);
		possible.add(ProductType.Weintraube);
		possible.add(ProductType.Zitrone);
	}

}
