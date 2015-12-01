package interfaces.controller;

import market.ProductType;
import controller.GlobalMarketAnalyzerController;

public abstract class GlobalMarketAnalyzer {

	public static GlobalMarketAnalyzer getGlobalMarketAnalyzer()
	{
		return new GlobalMarketAnalyzerController();
	}
	
	public abstract int getAmountAskedFor(ProductType product, int price);
	public abstract int getAmountProduced(ProductType product);
	public abstract void signUp(Player player);
}
