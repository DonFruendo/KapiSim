package market;

import java.util.ArrayList;
import java.util.Arrays;

public enum ProductType
{
	// Grundstoffe ohne Stahl ( wegen Abhängigkeit von Eisenerz, Kohle und Chemikalien)
	Strom	(Type.Grundstoff, new Dp[]{}),
	Wasser	(Type.Grundstoff, new Dp[]{new Dp(Strom, 0.1)}),
	Saatgut	(Type.Grundstoff, new Dp[]{new Dp(Wasser, 1)}),
	Holz	(Type.Grundstoff, new Dp[]{new Dp(Strom, 50), new Dp(Wasser, 50), new Dp(Saatgut, 1)}),
	Steine	(Type.Grundstoff, new Dp[]{new Dp(Strom, 5), new Dp(Wasser, 1)}),
	//Stahl	(Type.Grundstoff) --> verschoben
	
	
	// ** Food **
	// Obst
	Apfel		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Birne		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Banane		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Erdbeere	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Orange		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Zitrone		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	
	// Anbauten
	Kartoffel	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 5)}),
	Getreide	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 10)}),
	Kaffeebohne	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 15)}),
	Kakao		(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 15)}),
	Weintraube	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 20)}),
	Zuckerrohr	(Type.Food, new Dp[]{new Dp(Saatgut, 1), new Dp(Wasser, 50)}),
	Kautschuk	(Type.Food, new Dp[]{new Dp(Saatgut, 2), new Dp(Wasser, 21)}),
	
	// Backwaren
	Mehl		(Type.Food, new Dp[]{new Dp(Getreide, 10), new Dp(Wasser, 10)}),
	Ei			(Type.Food, new Dp[]{new Dp(Getreide, 0.5), new Dp(Wasser, 1)}),
	Milch		(Type.Food, new Dp[]{new Dp(Getreide, 0.2), new Dp(Wasser, 0.2)}),
	Zucker		(Type.Food, new Dp[]{new Dp(Strom, 20), new Dp(Zuckerrohr, 3)}),
	
	// Pulver
	Kaffeepulver(Type.Food, new Dp[]{new Dp(Kaffeebohne, 1.5), new Dp(Strom, 100)}),
	Kakaopulver	(Type.Food, new Dp[]{new Dp(Kakao, 1), new Dp(Zucker, 0.5)}),
	
	// Eis
	Bananeneis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Banane, 2)}),
	Organeneis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Orange, 2)}),
	Erdbeereis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Erdbeere, 2)}),
	Kaffeeeis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Kaffeebohne, 2)}),
	Schokoeis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Kakao, 2)}),
	Zitroneneis	(Type.Food, new Dp[]{new Dp(Milch, 1), new Dp(Zitrone, 2)}),
	
	//Getränke
	KapiCola	(Type.Food, new Dp[]{new Dp(Wasser, 1), new Dp(Zucker, 0.4), new Dp(Kaffeebohne, 0.1)}),
	Apfelsaft	(Type.Food, new Dp[]{new Dp(Zucker, 0.5), new Dp(Apfel, 1)}),
	Orangensaft	(Type.Food, new Dp[]{new Dp(Zucker, 0.5), new Dp(Orange, 1)}),
	
	// Süßigkeiten
	Bonbon		(Type.Food, new Dp[]{new Dp(Zucker, 0.7), new Dp(Wasser, 0.1)}),
	Schokoriegel(Type.Food, new Dp[]{new Dp(Zucker, 0.5), new Dp(Kakao, 0.2)}),
	
	// Brot
	Brötchen	(Type.Food, new Dp[]{new Dp(Mehl, 0.1), new Dp(Wasser, 1)}),
	Brot		(Type.Food, new Dp[]{new Dp(Mehl, 0.25), new Dp(Wasser, 1)}),
	
	// Tiere
	Huhn		(Type.Food, new Dp[]{new Dp(Getreide,  5), new Dp(Wasser, 10)}),
	Lamm		(Type.Food, new Dp[]{new Dp(Getreide, 20), new Dp(Wasser, 350)}),
	Rind		(Type.Food, new Dp[]{new Dp(Getreide, 20), new Dp(Wasser, 350)}),
	Schwein		(Type.Food, new Dp[]{new Dp(Getreide, 30), new Dp(Wasser, 350)}),
	
	// Tierische Produkte
	Lammfleisch		(Type.Food, new Dp[]{new Dp(Strom, 50), new Dp(Lamm, 0.1)}),
	Rindfleisch		(Type.Food, new Dp[]{new Dp(Strom, 50), new Dp(Rind, 0.1)}),
	Schweinefleisch	(Type.Food, new Dp[]{new Dp(Strom, 10), new Dp(Schwein, 0.1)}),
	Tierfutter		(Type.Food, new Dp[]{new Dp(Strom, 50), new Dp(Lamm, 0.1)}),
	
	// Tierische Endprodukte
	Wurst		(Type.Food, new Dp[]{new Dp(Strom, 50), new Dp(Schweinefleisch, 0.5)}),
	Hackfleisch	(Type.Food, new Dp[]{new Dp(Strom, 50), new Dp(Rindfleisch, 0.5)}),
	
	/* Noch zu ergänzen:
	 * Bier
	 * Flaschenkakao
	 * Wein
	 */
	
	// ** NonFood **
	Öl 	(Type.Nonfood, new Dp[]{new Dp(Strom, 100)});
	
	
	
	// ** Rest **
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
