package views;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleView extends JPanel {
	private static final long serialVersionUID = 7667002905207717251L;
	
	JScrollPane mainPanel;
	JTextArea console;
	
	public ConsoleView()
	{
		super();
		loadConsole();
		mainPanel = new JScrollPane(console);
		this.add(mainPanel);
		this.validate();
	}
	
	public void loadConsole()
	{
		console = new JTextArea(7, 70);
		console.setEditable(false);
	}
	
	public void addToConsole(String message)
	{
		console.append(message + "\n");
		mainPanel.getViewport().add(console);
	}
}
