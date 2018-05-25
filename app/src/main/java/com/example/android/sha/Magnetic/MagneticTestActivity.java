package com.example.android.sha.Magnetic;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sha.R;


public class MagneticTestActivity extends AppCompatActivity implements SensorEventListener {

    TextView x,y,z,teslatxt;
    Sensor sensor;
    SensorManager sensorManager;
    Button testEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_test);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
        teslatxt = (TextView) findViewById(R.id.teslaTxt);
        testEnd = (Button) findViewById(R.id.testEnd);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(MagneticTestActivity.this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MagneticTestActivity.this, MagneticResultActivity.class));
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float px = Math.round(sensorEvent.values[0]);
        float py = Math.round(sensorEvent.values[1]);
        float pz = Math.round(sensorEvent.values[2]);

        x.setText("X: " + String.valueOf(px));
        y.setText("Y: " + String.valueOf(py));
        z.setText("Z: " + String.valueOf(pz));

        double tesla = Math.round(Math.sqrt((px * px)+(py * py) + (pz * pz)));
        teslatxt.setText(String.format(tesla + " µT"));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
