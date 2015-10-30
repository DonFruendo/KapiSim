package production;

import controller.PlayerController;
import controller.ProductionBuildingController;
import market.ProductType;

public class Quelle extends ProductionBuildingController 
{
	public Quelle(PlayerController owner)
	{
		super(owner);
		addPossible(ProductType.Wasser,	  2);
		addPossible(ProductType.Oel,	948);
	}
}
