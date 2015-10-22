package game;

import market.Market;
import market.MarketGUI;
import market.Offer;
import market.ProductType;
import production.Kraftwerk;

public class TestLauncher
{
	static Player p1 = new Player(1);
	static Player p2 = new Player(2);
	
	public static void main(String[] args)
	{
		p1.kaps = 10000;
		Product p = new Product();
		p.type = ProductType.Strom;
		p.quality = 0;
		p1.inventory.addToInventory(p, 120);
		System.out.println(p1.inventory);
		Offer off = new Offer(p1, p, 50, 10.3);
		
		Market market = new Market();
		market.placeOffer(off);
		MarketGUI mgui = new MarketGUI(market);
		mgui.display();
		
		System.out.println(p1.inventory);
		
		p2.kaps = 1000;
		
		market.takeOffer(p2, 0);
		
		mgui.display();
		System.out.println(p2.inventory);
		
		System.out.println(p1.kaps);
		Kraftwerk kw = new Kraftwerk(p1);
		kw.produce(ProductType.Wasser, 1, 700);
		System.out.println(p1.inventory);
		System.out.println(p1.kaps);
	}
}
