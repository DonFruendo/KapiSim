package production;

import Inventory.Player;
import market.ProductType;

public class Getraenkefabrik extends ProductionBuildingController {

	public Getraenkefabrik(Player owner) {
		super(owner);
		possible.add(ProductType.Apfelsaft);
		//possible.add(ProductType.Bier);
		possible.add(ProductType.KapiCola);
		//possible.add(ProductType.Flaschenkakao);
		possible.add(ProductType.Orangensaft);
		//possible.add(ProductType.Wein);
	}

}
