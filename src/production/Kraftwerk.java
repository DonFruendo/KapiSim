package production;

import game.Player;
import market.ProductType;

public class Kraftwerk extends ProductionBuilding
{
	public Kraftwerk(Player owner)
	{
		super(owner);
		possible.add(ProductType.Strom);
	}
}
