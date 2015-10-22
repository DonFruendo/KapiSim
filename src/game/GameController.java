package game;

import market.Market;
import production.Kraftwerk;
import production.Quelle;

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
		p.productionBuildings.add(k1);
		p.productionBuildings.add(q1);
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
