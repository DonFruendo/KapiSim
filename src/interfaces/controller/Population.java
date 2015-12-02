package interfaces.controller;

import java.util.ArrayList;

import population.Consumer;
import population.Household;
import controller.PopulationController;

public abstract class Population
{
	public static Population getController()
	{
		return PopulationController.getController();
	}
	public abstract void populate();
	public abstract void printPopulation();
	public abstract ArrayList<Household> getHouseholds();
	public abstract ArrayList<Consumer> getConsumers();
}
