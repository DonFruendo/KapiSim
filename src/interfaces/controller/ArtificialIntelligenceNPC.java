package interfaces.controller;

import java.util.Map;

import market.ProductType;
import controller.PlayerController;

public abstract class ArtificialIntelligenceNPC extends PlayerController
{

	public ArtificialIntelligenceNPC()
	{
		super();
	}
	
	public abstract void tick();
	public abstract Map<ProductType, Integer> getProductionPlans();
}
