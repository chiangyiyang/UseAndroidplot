package com.yiyang.useandroidplot;

import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

public class SimpleXYPlotActivity extends AppCompatActivity {
    private XYPlot plot;    // variable for XYPlot widget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_xyplot);

        plot = (XYPlot) findViewById(R.id.plot);                        // set variable to instance
        Number[] serise1Nmubers = {10, 30, 25, 26, 28, 23, 20, 18, 20, 23, 10};    // set values for charting, the number of values is important( There are 11 values in this array)
        Number[] serise2Nmubers = {10, 20, 10, 36, 38, 13, 10, 28, 10, 33, 10};    // set values for charting, the number of values is important( There are 11 values in this array)
        final String[] domainLabels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        plot.setRangeBoundaries(0, 50, BoundaryMode.FIXED);

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(serise1Nmubers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Serise1");

        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(serise2Nmubers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Serise2");

        // create formatters to use for drawing a series using LineAndPointRenderer
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(this, R.xml.androidplot_format_series_1);

        LineAndPointFormatter series2Format =
                new LineAndPointFormatter(this, R.xml.androidplot_format_series_2);

        // Set Line pattern to DashPath
        series1Format.getLinePaint().setPathEffect(
                new DashPathEffect(new float[]{PixelUtils.dpToPix(10), PixelUtils.dpToPix(15)}, 0));

        series2Format.getLinePaint().setPathEffect(
                new DashPathEffect(new float[]{PixelUtils.dpToPix(20), PixelUtils.dpToPix(15)}, 0));

        // Set line path to be smooth
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(5, CatmullRomInterpolator.Type.Centripetal));

        series2Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(2, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot
        plot.addSeries(series2, series2Format);
        plot.addSeries(series1, series1Format);

        // Set label format of axis X to integer
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
                int i = Math.round(((Number) o).floatValue());
                return stringBuffer.append(domainLabels[i]);
            }

            @Override
            public Object parseObject(String s, ParsePosition parsePosition) {
                return null;
            }
        });

    }
}
