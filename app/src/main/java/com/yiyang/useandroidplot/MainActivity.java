package com.yiyang.useandroidplot;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSimpleXYPlot = (Button) findViewById(R.id.btnSimpleXYPlot);
        btnSimpleXYPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SimpleXYPlotActivity.class);
                startActivity(intent);

            }
        });

        Button btnOrientationSensor = (Button) findViewById(R.id.btnOrientationSensor);
        btnOrientationSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, OrientationSensorActivity.class);
                startActivity(intent);

            }
        });
    }
}
