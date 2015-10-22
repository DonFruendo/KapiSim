package production;

import game.Player;
import market.ProductType;
import java.util.ArrayList;

public class Kraftwerk extends ProductionBuilding
{
	final ArrayList<ProductType> possible;
	
	public Kraftwerk(Player owner)
	{
		super(owner);
		possible = new ArrayList<ProductType>();
		possible.add(ProductType.Strom);
	}

	@Override
	boolean productionIsPossible(ProductType p)
	{
		return possible.contains(p);
	}
	
	@Override
	public ArrayList<ProductType> getPossibleTypes()
	{
		return possible;
	}
}
