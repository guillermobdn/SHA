package com.example.android.sha.Gyroscope;

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

public class GyroscopeInfoActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_info);

        Button test = (Button) findViewById(R.id.gyroscopeTest);
        TextView tvInfo = (TextView) findViewById(R.id.info);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        tvInfo.setText(Html.fromHtml("<p><b>Nombre: </b>"+ gyroscopeSensor.getName() +"</p><p><b>Vendedor: </b>" + gyroscopeSensor.getVendor() +
                "</p><p><b>Versión: </b>"+ gyroscopeSensor.getVersion() + "</p><p><b>Energía: </b>" + gyroscopeSensor.getPower() + "</p><p><b>Rango máximo: </b>" +
                gyroscopeSensor.getMaximumRange() + "</p><p><b>Resolución: </b>" + gyroscopeSensor.getResolution()+ "</p>"));

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GyroscopeInfoActivity.this, GyroscopeTestActivity.class));
            }
        });
    }
}
