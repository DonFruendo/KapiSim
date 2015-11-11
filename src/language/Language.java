package language;

public abstract class Language
{
	public String mainGUICaption;
	
	public String KapiSim_Alpha;
	public String production;
	public String inventory;
	public String market;
	public String offer;
	
	public String quantity;
	public String product;
	public String produce;
	public String quality;
	public String corporation;
	public String price_per_unit;
	public String in_Stock;
	public String total;
	public String buy;
	public String sell;
	public String place_New_Offer;
	
	public String profile;
	public String name;
	
	public String needs;
	public String costs;
	
	public String submit;
	
	public String error;
	public String questionmark;
	
	public String kaps;
	
	public Language()
	{
		// Default
		mainGUICaption = "KapiSim 0.2.1a";
		
		KapiSim_Alpha = "KapiSim Alpha";
		production = "Production";
		inventory = "Inventory";
		market = "Market";
		offer = "Offer";
		
		quantity = "Quantity";
		product = "Product";
		produce = "Produce";
		quality = "Quality";
		corporation = "Corp.";
		price_per_unit = "Price/unit";
		in_Stock = "In Stock";
		total = "Total";
		buy = "Buy";
		sell = "Sell";
		place_New_Offer = "Place New Offer";
		
		profile = "Profile";
		name = "Name";
		
		needs = "Needs";
		costs = "Costs";
		
		submit = "Submit";
		
		error = "Error";
		questionmark = "?";
		
		kaps = "Kaps";
	}
}
