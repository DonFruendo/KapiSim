package game;

import market.ProductType;
import interfaces.controller.Game;
import interfaces.controller.Population;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		Population pop = Population.getController();
		pop.populate();
		//pop.printPopulation();
		
		Game gc = Game.getController();
		gc.startGame();
		
		for(ProductType p: ProductType.values())
		{
			System.out.print(p + "\t");
		}
	}
	
}
