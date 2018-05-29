package com.example.android.sha.Proximity;

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

public class ProximityInfoActivity extends AppCompatActivity {

    Sensor s;
    SensorManager sensorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_info);

        Button test = (Button) findViewById(R.id.proximityTest);
        TextView tvInfo = (TextView) findViewById(R.id.info);

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        tvInfo.setText(Html.fromHtml("<p><b>Nombre: </b>"+ s.getName() +"</p><p><b>Vendedor: </b>" + s.getVendor() +
                "</p><p><b>Versión: </b>"+ s.getVersion() + "</p><p><b>Energía: </b>" + s.getPower() + "</p><p><b>Rango máximo: </b>" +
                s.getMaximumRange() + "</p><p><b>Resolución: </b>" + s.getResolution()+"</p>"));

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProximityInfoActivity.this, ProximityTestActivity.class));
            }
        });
    }
}
