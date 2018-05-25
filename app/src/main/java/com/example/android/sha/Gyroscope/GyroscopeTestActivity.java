package com.example.android.sha.Gyroscope;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.android.sha.R;

public class GyroscopeTestActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor, rotationVectorSensor;
    Button activador, testEnd;
    TextView textGyroscope;
    ImageView fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_test);

        fondo = (ImageView) findViewById(R.id.fondo);
        textGyroscope =  (TextView) findViewById(R.id.textGyroscope);
        activador = (Button) findViewById(R.id.activador);
        testEnd = (Button) findViewById(R.id.testEnd);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        activador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                System.out.println("GIROSCOPE  " + gyroscopeSensor);
                sensorManager.registerListener(GyroscopeTestActivity.this, gyroscopeSensor,
                        SensorManager.SENSOR_DELAY_NORMAL);

                if(gyroscopeSensor == null && rotationVectorSensor == null){
                    activador.setVisibility(View.INVISIBLE  );
                    textGyroscope.setText("Este móvil no tiene giroscopio");
                } else {
                    activador.setVisibility(View.INVISIBLE  );
                    textGyroscope.setTextSize(20.0f);

                    SensorEventListener rvListener = new SensorEventListener() {
                        @Override
                        public void onSensorChanged(SensorEvent sensorEvent) {
                            // More code goes here
                            float[] rotationMatrix = new float[16];
                            SensorManager.getRotationMatrixFromVector(
                                    rotationMatrix, sensorEvent.values);

                            // Remap coordinate system
                            float[] remappedRotationMatrix = new float[16];
                            SensorManager.remapCoordinateSystem(rotationMatrix,
                                    SensorManager.AXIS_X,
                                    SensorManager.AXIS_Z,
                                    remappedRotationMatrix);

                            // Convert to orientations
                            float[] orientations = new float[3];
                            SensorManager.getOrientation(remappedRotationMatrix, orientations);
                            for(int i = 0; i < 3; i++) {
                                orientations[i] = (float)(Math.toDegrees(orientations[i]));
                            }

                            int px = Math.round(orientations[0]);
                            int py = Math.round(orientations[1]);
                            int pz = Math.round(orientations[2]);

                            textGyroscope.setText("X: " + px + ", Y: " + py + ", Z: " + pz);

                        }
                        @Override
                        public void onAccuracyChanged(Sensor sensor, int i) {
                        }
                    };
                    sensorManager.registerListener(rvListener,
                            rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
                }

            }
        });


        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GyroscopeTestActivity.this, GyroscopeResultActivity.class));
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // More code goes here
        if(sensorEvent.values[2] > 0.5f) { // anticlockwise
            fondo.setBackgroundColor(Color.MAGENTA);
        } else if(sensorEvent.values[2] < -0.5f) { // clockwise
            fondo.setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
