package production;

import Inventory.Player;
import market.ProductType;

public class Quelle extends ProductionBuildingController 
{
	public Quelle(Player owner)
	{
		super(owner);
		possible.add(ProductType.Wasser);
		possible.add(ProductType.Öl);
	}
}
