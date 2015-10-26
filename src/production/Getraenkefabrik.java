package production;

import controller.PlayerController;
import market.ProductType;

public class Getraenkefabrik extends ProductionBuildingController {

	public Getraenkefabrik(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Apfelsaft);
		//possible.add(ProductType.Bier);
		possible.add(ProductType.KapiCola);
		//possible.add(ProductType.Flaschenkakao);
		possible.add(ProductType.Orangensaft);
		//possible.add(ProductType.Wein);
	}

}
