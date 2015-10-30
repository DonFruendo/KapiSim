package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Plantage extends ProductionBuildingController {

	public Plantage(PlayerController owner) {
		super(owner);
		addPossible(ProductType.Saatgut,	  1);
		//addPossible(ProductType.Baumwolle,	 90);
		addPossible(ProductType.Holz,	   1032);
		addPossible(ProductType.Getreide,	  9);
		addPossible(ProductType.Kaffeebohne,135);
		addPossible(ProductType.Kakao,		  9);
		addPossible(ProductType.Kartoffel,	 14);
		addPossible(ProductType.Kautschuk,	450);
		addPossible(ProductType.Zuckerrohr,	 14);
	}

}
