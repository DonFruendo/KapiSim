package production;

import controller.PlayerController;
import market.ProductType;

public class Quelle extends ProductionBuildingController 
{
	public Quelle(PlayerController owner)
	{
		super(owner);
		possible.add(ProductType.Wasser);
		possible.add(ProductType.Öl);
	}
}
