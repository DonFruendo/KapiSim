package production;

import market.ProductType;
import game.Player;

public class Plantage extends ProductionBuilding {

	public Plantage(Player owner) {
		super(owner);
		possible.add(ProductType.Saatgut);
	}

}
