package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Kraftwerk extends ProductionBuildingController
{
	public Kraftwerk(PlayerController owner)
	{
		super(owner);
		possible.add(ProductType.Strom);
	}
}
