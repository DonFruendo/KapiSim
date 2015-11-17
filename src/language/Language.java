package language;

public abstract class Language
{
	public String mainGUICaption;
	
	public String basicProductsLtd;
	
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
	
	public String log_Debug;
	public String log_Warning;
	public String log_Error;
	public String log_Log;
	
	
	public String not_enough;
	public String in_stock_of;
	public String to_pay_market_fee;
	public String needed_to_offer_this;
	public String has_not_enough_kaps;
	public String is_not_allowed_to_take_this_offer;
	public String has_been_removed_from_inventory;
	public String has_not_enough_kaps_to_produce;
	public String was_taken;
	public String is_missing;
	public String needed;
	
	public Language()
	{
		// Default
		mainGUICaption = "KapiSim 0.2.1a";
		
		basicProductsLtd = "BasicProducts Ltd";
		
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
		
		log_Debug = "Debug";
		log_Warning = "Warning";
		log_Error = "ERROR";
		
		
		not_enough = "Not enough";
		in_stock_of = "in stock of";
		to_pay_market_fee = "to pay market fee";
		needed_to_offer_this = "needed to offer this";
		has_not_enough_kaps = "has not enough " + kaps;
		is_not_allowed_to_take_this_offer = "is not allowed to take this offer";
		has_been_removed_from_inventory = "has been removed from the inventory";
		has_not_enough_kaps_to_produce = has_not_enough_kaps + " to " + produce;
		was_taken = "was taken";
		is_missing = "is missing";
		needed = "needed";
		
	}
}
