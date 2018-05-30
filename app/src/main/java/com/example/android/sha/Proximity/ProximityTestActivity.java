package com.example.android.sha.Proximity;

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

public class ProximityTestActivity extends AppCompatActivity implements SensorEventListener{

    ImageView fondo;
    Sensor s;
    SensorManager sensorM;
    TextView prox;
    Button testEnd;
    boolean alltest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_test);

        fondo = (ImageView) findViewById(R.id.fondo);
        prox = (TextView) findViewById(R.id.text1);
        testEnd = (Button) findViewById(R.id.testEnd);

    /*
    Se captura desde el visual el LinearLayuot y el botón gracias a los ID fondo y button1 que se le asignó a ambos componentes en el XML
    */
        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorM.registerListener(ProximityTestActivity.this,s,
                SensorManager.SENSOR_DELAY_NORMAL);

    /*
    Al botón activador se le agrega un evento onClick (Significa al hacer click) que permite, que al pulsar sobre él, se ejecute la acción programada. Para esto se crea un Listener(Escucha) de tipo OnClick y se le adiciona al botón.
    En su evento principal se escribe el código que activa el sensor: Se le asigna al Manejador del Sensor (sensorM) el servicio del sistema que controla los sensores, después al sensor que se creó se le agrega específicamente el tipo de sensor que queremos utilizar, en este caso de proximidad y se le cambia el texto al botón para que diga “Activado”. Con esto ya está activado el sensor y procesando los datos que  recibe.
    */
        final Intent resultactivity = new Intent(ProximityTestActivity.this, ProximityResultActivity.class);

        alltest = getIntent().getBooleanExtra("alltest",false);

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultactivity.putExtra("alltest",alltest);
                startActivity(new Intent(resultactivity));
            }
        });

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent evento) {

        if (evento.values[0] < s.getMaximumRange()) {
            fondo.setImageResource(R.drawable.eye_close);
            prox.setText("0.0 CM");
        }else {
            fondo.setImageResource(R.drawable.eye_open);
            prox.setText(s.getMaximumRange() + " CM");
        }
    }
}
