package game;

import market.Market;
import production.*;

public class GameController
{
	Market market;
	Player p;
	
	public GameController()
	{
		market = new Market();
		p = new Player(11);
		p.kaps = 1000;
		Kraftwerk k1 = new Kraftwerk(p);
		Quelle q1 = new Quelle(p);
		Plantage p1 = new Plantage(p);
		Obstplantage o1 = new Obstplantage(p);
		Viehzucht  v1 = new Viehzucht(p);
		Lebensmittelproduktion l1 = new Lebensmittelproduktion(p);
		Getraenkefabrik g1 = new Getraenkefabrik(p);
		Fleischerei f1 = new Fleischerei(p);
		Eismanufaktur e1 = new Eismanufaktur(p);
		p.productionBuildings.add(k1);
		p.productionBuildings.add(q1);
		p.productionBuildings.add(o1);
		p.productionBuildings.add(v1);
		p.productionBuildings.add(p1);
		p.productionBuildings.add(l1);
		p.productionBuildings.add(g1);
		p.productionBuildings.add(f1);
		p.productionBuildings.add(e1);
		
	}
	
	public Market getMarket()
	{
		return market;
	}
	
	public Player getPlayer()
	{
		return p;
	}
}
