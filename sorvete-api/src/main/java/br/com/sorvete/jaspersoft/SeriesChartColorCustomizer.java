package br.com.sorvete.jaspersoft;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.TextAnchor;

import net.sf.jasperreports.engine.JRAbstractChartCustomizer;
import net.sf.jasperreports.engine.JRChart;

public class SeriesChartColorCustomizer extends JRAbstractChartCustomizer {

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {

		var plot = chart.getCategoryPlot();

		var renderer = plot.getRenderer();

		if (chart.getLegend() != null) {
			chart.getLegend().setBorder(0.0, 0.0, 0.0, 0.0);
		}

		if (renderer instanceof BarRenderer) {
			var barRenderer = (BarRenderer) plot.getRenderer();
			barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2));
			plot.setRenderer(renderer);

		}

	}

}