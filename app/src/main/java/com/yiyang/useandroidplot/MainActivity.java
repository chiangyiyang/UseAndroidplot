package com.yiyang.useandroidplot;

import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidplot.util.PixelUtils;
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

public class MainActivity extends AppCompatActivity {

    private XYPlot plot;    // variable for XYPlot widget

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plot = (XYPlot) findViewById(R.id.plot);                        // set variable to instance
        Number[] serise1Nmubers = {10, 30, 25, 26, 28, 23, 20, 18, 20, 23, 10};    // set values for charting, the number of values is important( There are 11 values in this array)

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(serise1Nmubers),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Serise1");

        // create formatters to use for drawing a series using LineAndPointRenderer
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(this, R.xml.androidplot_format_series_1);

        // Set Line pattern to DashPath
        series1Format.getLinePaint().setPathEffect(
                new DashPathEffect(new float[]{PixelUtils.dpToPix(10), PixelUtils.dpToPix(15)}, 0));

        // Set line path to be smooth
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(5, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot
        plot.addSeries(series1, series1Format);

        // Set label format of axis X to integer
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object o, StringBuffer stringBuffer, FieldPosition fieldPosition) {
                int i = Math.round(((Number) o).floatValue());
                return stringBuffer.append(i);
            }

            @Override
            public Object parseObject(String s, ParsePosition parsePosition) {
                return null;
            }
        });

    }
}
