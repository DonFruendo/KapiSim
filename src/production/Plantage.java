package production;

import market.ProductType;
import game.Player;

public class Plantage extends ProductionBuilding {

	public Plantage(Player owner) {
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
