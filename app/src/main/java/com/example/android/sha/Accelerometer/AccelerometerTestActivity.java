package com.example.android.sha.Accelerometer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sha.R;

public class AccelerometerTestActivity extends AppCompatActivity implements SensorEventListener{

    ImageView acceImg, ball;
    TextView text1;
    Button continuar;
    Sensor mySensor;
    SensorManager sensorManager;
    ConstraintLayout pantalla;
    boolean alltest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        acceImg = (ImageView) findViewById(R.id.acceImg);
        text1 = (TextView) findViewById(R.id.text1);
        continuar = (Button) findViewById(R.id.continuar);
        pantalla = (ConstraintLayout) findViewById(R.id.pantalla);
        ball = (ImageView) findViewById(R.id.ball);

        ball.setVisibility(View.INVISIBLE);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                sensorManager.registerListener(AccelerometerTestActivity.this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

                ball.setVisibility(View.VISIBLE);
                acceImg.setVisibility(View.INVISIBLE);
                continuar.setText("Finalizar test");

                final Intent resultactivity = new Intent(AccelerometerTestActivity.this, AccelerometerResultActivity.class);

                alltest = getIntent().getBooleanExtra("alltest",false);

                continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resultactivity.putExtra("alltest",alltest);
                        startActivity(new Intent(resultactivity));
                    }
                });

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        text1.setTextSize(20.0f);
        float px = (float) (Math.round(event.values[0]*100.0)/100.0);
        float py = (float) (Math.round(event.values[1]*100.0)/100.0);
        float pz = (float) (Math.round(event.values[2]*100.0)/100.0);

        text1.setText("X: " + px + ", Y: " + py + ", Z: " + pz);

        float valorx = -event.values[0]*200;
        float valory = event.values[1]*200;

        if (valory <= pantalla.getMinHeight()){
            ball.setY(pantalla.getMinHeight());
        }else {
            ball.setY(valory);
        }
        if (valorx <= pantalla.getMinWidth()){
            ball.setX(pantalla.getMinWidth());
        }else {
            ball.setX(valorx);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
