package com.example.android.sha.Accelerometer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sha.R;

public class AccelerometerInfoActivity extends AppCompatActivity {

    Sensor mySensor;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_info);

        Button test = (Button) findViewById(R.id.accelerometerTest);
        TextView tvInfo = (TextView) findViewById(R.id.info);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tvInfo.setText(Html.fromHtml("<p><b>Nombre: </b>"+ mySensor.getName() +"</p><p><b>Vendedor: </b>" + mySensor.getVendor() +
                "</p><p><b>Versión: </b>"+ mySensor.getVersion() + "</p><p><b>Energía: </b>" + mySensor.getPower() + "</p><p><b>Rango máximo: </b>" +
                mySensor.getMaximumRange() + "</p><p><b>Resolución: </b>" + mySensor.getResolution() + "</p>"));

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccelerometerInfoActivity.this, AccelerometerTestActivity.class));
            }
        });
    }
}
