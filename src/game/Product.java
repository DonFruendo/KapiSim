package game;

import market.ProductType;

/*
 * quality not implemented!
 */
public class Product
{
	public ProductType type;
	public int quality;
	
	public Product()
	{
		
	}
	
	public Product(ProductType type, int quality)
	{
		this.type = type;
		//this.quality = quality;
		this.quality = 0;
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Product))
		{
			return false;
		}
		Product p = (Product) obj;
		return ((type == p.type) && (quality == p.quality));
	}
	
	public String toString()
	{
		return "" + type + " Q" + quality + "";
	}
}
