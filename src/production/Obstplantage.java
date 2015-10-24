package production;

import market.ProductType;
import game.Player;

public class Obstplantage extends ProductionBuilding {

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
