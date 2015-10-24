package production;

import market.ProductType;
import game.Player;

public class Viehzucht extends ProductionBuilding {

	public Viehzucht(Player owner) {
		super(owner);
		possible.add(ProductType.Ei);
		possible.add(ProductType.Huhn);
		possible.add(ProductType.Lamm);
		possible.add(ProductType.Milch);
		possible.add(ProductType.Rind);
		possible.add(ProductType.Schwein);
		//possible.add(ProductType.Wolle);
	}

}
