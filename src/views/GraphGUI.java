package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import interfaces.controller.GlobalMarketAnalyzer;
import market.ProductType;

@SuppressWarnings("serial")
public class GraphGUI extends JFrame {
	JPanel mainPanel;
	JPanel choosePanel;
	JScrollPane graphPane;
	JFreeChart lineChart;
	ChartPanel chartPanel;
	
	public GraphGUI(String applicationTitle) 
	{
		super(applicationTitle);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		choosePanel = new JPanel();
		final JComboBox<ProductType> cbProduct = new JComboBox<ProductType>(ProductType.values());
		choosePanel.add(cbProduct);
		JButton btSelect = new JButton("Select");
		btSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ProductType product = (ProductType) (cbProduct.getSelectedItem());
				loadGraphPanel(product);
			}
		});
		choosePanel.add(btSelect);
		
		mainPanel.add(choosePanel);
		graphPane = new JScrollPane();
		loadGraphPanel(ProductType.values()[0]);
		mainPanel.add(graphPane);
		setContentPane(mainPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}
	
	private void loadGraphPanel(ProductType product)
	{
		lineChart = ChartFactory.createLineChart(
				product.toString(), 
				"Price", 
				"Amount", 
				createDataset(product),
				PlotOrientation.VERTICAL, 
				true, true, false);
		CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		graphPane.getViewport().add(chartPanel);
	}
	
	private DefaultCategoryDataset createDataset(ProductType product) 
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 15; i < 45; i++)
		{
			System.out.println("starting analysis of " + i);
			GlobalMarketAnalyzer gma = GlobalMarketAnalyzer.getGlobalMarketAnalyzer();
			int amount = gma.getAmountAskedFor(product, i);
			dataset.addValue(amount, "Demand", i + "");
			dataset.addValue(amount * 0.8, "Production", i + "");
			System.out.println("starting analysis of " + i);
		}
		return dataset;
	}

	public void reloadGraph(ProductType product)
	{
		
	}
}