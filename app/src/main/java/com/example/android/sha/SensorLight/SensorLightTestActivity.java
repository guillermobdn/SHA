package com.example.android.sha.SensorLight;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sha.Battery.BatteryResultActivity;
import com.example.android.sha.Battery.BatteryTestActivity;
import com.example.android.sha.R;

public class SensorLightTestActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor mLight;
    TextView textView;
    ImageView imageView;
    Button testEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_light_test);
        imageView = (ImageView)findViewById(R.id.imageSensor);
        textView = (TextView)findViewById(R.id.text1);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        testEnd = (Button)findViewById(R.id.testEnd) ;

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SensorLightTestActivity.this, SensorLightResultActivity.class));
            }
        });
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            textView.setText(" " + event.values[0] + " luxes");

            if (event.values[0] <= 1){
                imageView.setImageResource(R.drawable.light1);
            } else if (event.values[0] < 100 && event.values[0] > 1 ){
                imageView.setImageResource(R.drawable.light2);
            } else if (event.values[0] < 200 && event.values[0] > 100 ){
                imageView.setImageResource(R.drawable.light3);
            } else if (event.values[0] < 300 && event.values[0] > 200 ){
                imageView.setImageResource(R.drawable.light4);
            }else if (event.values[0] < 400 && event.values[0] > 300 ){
                imageView.setImageResource(R.drawable.light5);
            }else if (event.values[0] < 500 && event.values[0] > 400 ){
                imageView.setImageResource(R.drawable.light6);
            }else {
                imageView.setImageResource(R.drawable.light7);
            }
        }
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
