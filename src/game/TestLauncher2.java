package game;

import market.ProductType;
import controller.PopulationController;
import interfaces.controller.Game;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		Game gc = Game.getController();
		gc.startGame();
		
		PopulationController pop = new PopulationController();
		pop.populate();
		pop.printPopulation();
		
		for(ProductType p: ProductType.values())
		{
			System.out.print(p + "\t");
		}
	}
	
}
