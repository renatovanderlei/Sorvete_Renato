package br.com.sorvete.jaspersoft;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.TextAnchor;

import net.sf.jasperreports.engine.JRAbstractChartCustomizer;
import net.sf.jasperreports.engine.JRChart;

public class BarChartColorCustomizer extends JRAbstractChartCustomizer {

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {

		var plot = chart.getCategoryPlot();

		var renderer = plot.getRenderer();

		if (chart.getLegend() != null) {
			chart.getLegend().setBorder(0.0, 0.0, 0.0, 0.0);
		}

		if (renderer instanceof BarRenderer) {
			var barRenderer = (BarRenderer) plot.getRenderer();
			barRenderer.setItemMargin(-0.5);
			barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2));
			plot.setRenderer(renderer);
			

			var dataSet = plot.getDataset();
			int rowCount = dataSet.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				var rowKey = dataSet.getRowKey(i);
				var p = getCustomPaint(rowKey);
				if (p != null) {
					plot.getRenderer().setSeriesPaint(i, p);
				}
			}

			var meta = (Double) this.getParameterValue("meta");
			if (meta != null) {
				var vm = new ValueMarker(meta, Color.orange, new BasicStroke(1.0F));
				plot.addRangeMarker(vm);
			}
		}

	}

	private Paint getCustomPaint(Comparable<?> rowKey) {
		if ("PONTA".equals(rowKey)) {
			return new Color(247, 70, 74);
		}
		if ("RESERVADO".equals(rowKey)) {
			return new Color(54, 162, 0);
		}
		return new Color(54, 162, 235);
	}

}