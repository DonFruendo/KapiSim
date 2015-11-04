package interfaces.controller;

import java.util.ArrayList;

import market.ProductType;
import game.Product;

public interface ProductionBuilding
{
	public int getProductionCost(ProductType product);
	public void produce(ProductType pType, int quality, int quantity);
	public void produce(Product p, int quantity);
	public ArrayList<ProductType> getPossibleTypes();
}
