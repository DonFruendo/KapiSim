package game;

import market.ProductType;
import views.GraphGUI;

import javax.swing.JFrame;

import org.jfree.ui.RefineryUtilities;

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
		
		GraphGUI graph = new GraphGUI("Graph", "Strom");
	    RefineryUtilities.centerFrameOnScreen( graph );
		graph.setVisible(true);
		graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
