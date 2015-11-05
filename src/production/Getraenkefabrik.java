package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Getraenkefabrik extends ProductionBuildingController {

	public Getraenkefabrik(Player player) {
		super(player);
		addPossible(ProductType.Apfelsaft,		0);
		//addPossible(ProductType.Bier,			0);
		addPossible(ProductType.KapiCola,		0);
		//addPossible(ProductType.Flaschenkakao,	0);
		addPossible(ProductType.Orangensaft,	0);
		//addPossible(ProductType.Wein,			0);
	}

}
