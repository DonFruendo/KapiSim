package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Plantage extends ProductionBuildingController {

	public Plantage(PlayerController owner) {
		super(owner);
		possible.add(ProductType.Saatgut);
		// possible.add(ProductType.Baumwolle);
		possible.add(ProductType.Getreide);
		possible.add(ProductType.Holz);
		possible.add(ProductType.Kaffeebohne);
		possible.add(ProductType.Kakao);
		possible.add(ProductType.Kartoffel);
		possible.add(ProductType.Kautschuk);
		possible.add(ProductType.Zuckerrohr);
	}

}
