package market;

import java.util.ArrayList;
import java.util.Arrays;

public enum ProductType
{
	Strom	(Type.Grundstoff, new Dp[]{}),
	Wasser	(Type.Grundstoff, new Dp[]{new Dp(Strom, 0.1)}),
	Saatgut	(Type.Grundstoff, new Dp[]{new Dp(Wasser, 1)}),
	Holz	(Type.Grundstoff, new Dp[]{new Dp(Strom, 50), new Dp(Wasser, 50), new Dp(Saatgut, 1)}),
	Steine	(Type.Grundstoff, new Dp[]{new Dp(Strom, 5), new Dp(Wasser, 1)}),
	//Stahl	(Type.Grundstoff, new Dp[]{}),
	
	Öl 	(Type.Nonfood, new Dp[]{new Dp(Strom, 100)}),
	
	Getreide	(Type.Food, new Dp[]{new Dp(Wasser, 10), new Dp(Saatgut, 1)}),
	Milch		(Type.Food, new Dp[]{new Dp(Wasser, 0.2), new Dp(Getreide, 0.2)}),
	
	Apfel		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	//Apfelsaft	(Type.Food, new Dp[]{}),
	Banane		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Bananeneis	(Type.Food, new Dp[]{new Dp(Banane, 2), new Dp(Milch, 1)});
	
	int tier;
	Type type;
	ArrayList<Dp> dependsOn;
	
	ProductType(Type type, Dp[] depends)
	{
		this.type = type;
		dependsOn = new ArrayList<Dp>(Arrays.asList(depends));
		
		if(dependsOn.size() == 0)
		{
			tier = 0;
		}
		else
		{
			int maxT = 0;
			for(Dp dependency : dependsOn)
			{
				if(dependency.product.getTier() > maxT)
				{
					maxT = dependency.product.getTier();
				}
			}
			tier = maxT + 1;
		}
	}
	
	public Type getType()
	{
		return type;
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public ArrayList<Dp> getDependencies()
	{
		return dependsOn;
	}
	
	
	
	public static enum Type
	{
		Grundstoff,
		Food,
		Nonfood
	}
	
	public static class Dp
	{
		ProductType product;
		double value;
		
		public Dp(ProductType product, double value)
		{
			this.product = product;
			this.value = value;
		}
		
		public ProductType getType()
		{
			return product;
		}
		public double getValue()
		{
			return value;
		}
	}
}
