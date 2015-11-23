package interfaces.controller;

import market.ProductType;
import controller.GlobalMarketAnalyzerController;

public abstract class GlobalMarketAnalyzer {

	public GlobalMarketAnalyzer getGlobalMarketAnalyzer()
	{
		return new GlobalMarketAnalyzerController();
	}
	
	public abstract int getAmountAskedFor(ProductType product);
}
