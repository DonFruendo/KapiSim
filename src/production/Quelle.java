package production;

import game.Player;
import market.ProductType;

import java.util.ArrayList;

public class Quelle extends ProductionBuilding 
{
	final ArrayList<ProductType> possible;
	
	public Quelle(Player owner)
	{
		super(owner);
		possible = new ArrayList<ProductType>();
		possible.add(ProductType.Wasser);
		possible.add(ProductType.Öl);
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
