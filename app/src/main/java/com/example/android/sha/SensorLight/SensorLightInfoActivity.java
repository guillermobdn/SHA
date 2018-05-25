package com.example.android.sha.SensorLight;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sha.Battery.BatteryInfoActivity;
import com.example.android.sha.Battery.BatteryTestActivity;
import com.example.android.sha.R;

public class SensorLightInfoActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_light_info);

        Button test = (Button) findViewById(R.id.sensorLightTest);
        TextView tvInfo = (TextView) findViewById(R.id.info);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        tvInfo.setText(Html.fromHtml("<p><b>Nombre: </b>"+ mLight.getName() +"</p><p><b>Vendedor: </b>" + mLight.getVendor() +
                "</p><p><b>Versión: </b>"+ mLight.getVersion() + "</p><p><b>Energía: </b>" + mLight.getPower() + "</p><p><b>Rango máximo: </b>" +
        mLight.getMaximumRange() + "</p><p><b>Resolución: </b>" + mLight.getResolution()));


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SensorLightInfoActivity.this, SensorLightTestActivity.class));
            }
        });
    }
}
