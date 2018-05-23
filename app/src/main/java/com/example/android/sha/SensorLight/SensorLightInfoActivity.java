package com.example.android.sha.SensorLight;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SensorLightInfoActivity.this, SensorLightTestActivity.class));
            }
        });
    }
}
