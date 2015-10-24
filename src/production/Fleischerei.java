package production;

import market.ProductType;
import game.Player;

public class Fleischerei extends ProductionBuilding {

	public Fleischerei(Player owner) {
		super(owner);
		possible.add(ProductType.Hackfleisch);
		possible.add(ProductType.Lammfleisch);
		possible.add(ProductType.Rindfleisch);
		possible.add(ProductType.Schweinefleisch);
		possible.add(ProductType.Tierfutter);
		possible.add(ProductType.Wurst);
	}

}
