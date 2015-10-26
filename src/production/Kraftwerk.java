package production;

import Inventory.Player;
import market.ProductType;

public class Kraftwerk extends ProductionBuildingController
{
	public Kraftwerk(Player owner)
	{
		super(owner);
		possible.add(ProductType.Strom);
	}
}
