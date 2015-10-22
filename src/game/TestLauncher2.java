package game;

public class TestLauncher2 {

	public static void main(String[] args)
	{
		GameController gc = new GameController();
		GameGUI gui = new GameGUI(gc);
		gui.setVisible(true);
		
	}

}
