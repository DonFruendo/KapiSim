package views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import interfaces.controller.GlobalMarketAnalyzer;
import market.ProductType;

@SuppressWarnings("serial")
public class GraphGUI extends ApplicationFrame {
	public GraphGUI(String applicationTitle, String chartTitle) 
	{
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle, 
				"Price", 
				"Amount", 
				createDataset(),
				PlotOrientation.VERTICAL, 
				true, true, false);
		CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}

	private DefaultCategoryDataset createDataset() 
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 15; i < 45; i++)
		{
			System.out.println("starting analysis of " + i);
			GlobalMarketAnalyzer gma = GlobalMarketAnalyzer.getGlobalMarketAnalyzer();
			int amount = gma.getAmountAskedFor(ProductType.Strom, i);
			dataset.addValue(amount, "Demand", i + "");
			dataset.addValue(amount * 0.8, "Production", i + "");
			System.out.println("starting analysis of " + i);
		}

		/*
		 * dataset.addValue( 15 , "schools" , "1970" ); dataset.addValue( 30 ,
		 * "schools" , "1980" ); dataset.addValue( 60 , "schools" , "1990" );
		 * dataset.addValue( 120 , "schools" , "2000" ); dataset.addValue( 240 ,
		 * "schools" , "2010" ); dataset.addValue( 300 , "schools" , "2014" );
		 * //
		 */
		return dataset;
	}
	/*
	 * public static void main( String[ ] args ) { GraphGUI chart = new
	 * GraphGUI( "School Vs Years" , "Amount of Strom vs price");
	 * 
	 * chart.pack( ); RefineryUtilities.centerFrameOnScreen( chart );
	 * chart.setVisible( true ); }//
	 */
}