package production;

import game.Player;
import market.ProductType;

public class Quelle extends ProductionBuilding 
{
	public Quelle(Player owner)
	{
		super(owner);
		possible.add(ProductType.Wasser);
		possible.add(ProductType.Öl);
	}
}
