package main.java.gui;


import org.jfree.chart.ChartPanel;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoLinha {

	private DefaultCategoryDataset dataSetGlobal;
	
	ChartPanel chartPanel = null;

	
	public GraficoLinha() {
	}

	public ChartPanel getGraficoLinha(int x, int y, String periodo) {

		
		JFreeChart lineChart = ChartFactory.createLineChart("", periodo, "Medidas", dataSetGlobal,
				PlotOrientation.VERTICAL, true, true, false);

		chartPanel = new ChartPanel(lineChart);
		CategoryPlot plot = lineChart.getCategoryPlot();
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		plot.setRenderer(renderer);
		chartPanel.setPreferredSize(new java.awt.Dimension(x, y));
		chartPanel.setBackground(Color.white);
		chartPanel.setBounds(0, 0, x+100, y+100);
		
		
		

		return chartPanel;
	}
	
	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataSetGlobal = dataset;
		
		
	}
	
	
	public void atualizar() {
		chartPanel.repaint();
		chartPanel.updateUI();
	}

	
	
	
}