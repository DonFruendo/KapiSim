package Inventory;

import java.util.ArrayList;

import market.ProductType;
import game.Product;

public class Inventory
{
	ArrayList<Entry> inv;
	
	public Inventory()
	{
		inv = new ArrayList<Entry>();
	}
	
	public Inventory(Inventory another)
	{
		this.inv = another.inv;
	}
	
	public ArrayList<Entry> getWholeInventory()
	{
		return inv;
	}
	
	public void addtoInventory(Entry entry)
	{
		boolean c = true;
		for(Entry e: inv)
		{
			if(e.product.equals(entry.product))
			{
				e.quantity += entry.quantity;
				c = false;
				break;
			}
		}
		if(c)
			inv.add(entry);
	}
	
	public void addToInventory(Product product, int quantity)
	{
		addtoInventory(new Entry(product, quantity));
	}
	
	public boolean validateStock(Entry entry)
	{
		boolean c = false;
		Loop: for(Entry e : inv)
		{
			if(e.product.equals(entry.product))
			{
				if(e.quantity >= entry.quantity)
				{
					c = true;
					break Loop;
				}
			}
		}
		return c;
	}
	
	public boolean validateStock(Product product, int quantity)
	{
		return validateStock(new Entry(product, quantity));
	}
	
	public void removeFromInventory(ArrayList<Entry> entries)
	{
		for(Entry e: entries)
		{
			removeFromInventory(e);
		}
	}
	
	public void removeFromInventory(Entry entry)
	{
		if(validateStock(entry))
		{
			Loop: for(Entry e : inv)
			{
				if(e.product.equals(entry.product))
				{
					e.quantity -= entry.quantity;
					break Loop;
				}
			}
		}
	}
	
	public void removeFromInventory(Product product, int quantity)
	{
		removeFromInventory(new Entry(product, quantity));
	}
	
	public ArrayList<ProductType> getAllProducts()
	{
		// TODO Wenn Qualitaet ins Spiel kommt, sollte das hier geaendert werden..
		ArrayList<ProductType> allProducts = new ArrayList<ProductType>();
		for(Entry entry : inv)
		{
			allProducts.add(entry.getProductType());
		}
		return allProducts;
	}
	
	
	
	
	public String toString()
	{
		return inv.toString();
	}
	
	public static class Entry
	{
		Product product;
		int quantity;
		
		public Entry(Product product, int quantity)
		{
			this.product = product;
			this.quantity = quantity;
		}
		
		public String toString()
		{
			return "[" + quantity + "x " + product + "]";
		}
		
		public ProductType getProductType()
		{
			return product.type;
		}
		
		public int getQuality()
		{
			return product.quality;
		}
		
		public int getQuantity()
		{
			return quantity;
		}
	}
	
	public Inventory getCopy()
	{
		return new Inventory(this);
	}
}
