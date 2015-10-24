package production;

import market.ProductType;
import game.Player;

public class Getraenkefabrik extends ProductionBuilding {

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
