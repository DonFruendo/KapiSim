package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Quelle extends ProductionBuildingController 
{
	public Quelle(Player player)
	{
		super(player);
		addPossible(ProductType.Wasser,	  2);
		addPossible(ProductType.Oel,	948);
	}
}
