package production;

import interfaces.controller.Player;
import controller.ProductionBuildingController;
import market.ProductType;

public class Kraftwerk extends ProductionBuildingController
{
	public Kraftwerk(Player player)
	{
		super(player);
		addPossible(ProductType.Strom,	1);
	}
}
