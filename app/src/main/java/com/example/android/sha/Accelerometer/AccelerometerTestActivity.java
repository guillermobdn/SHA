package com.example.android.sha.Accelerometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sha.R;

public class AccelerometerTestActivity extends AppCompatActivity {

    ImageView acceImg;
    TextView text1;
    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        acceImg = (ImageView) findViewById(R.id.acceImg);
        text1 = (TextView) findViewById(R.id.text1);
        continuar = (Button) findViewById(R.id.continuar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceImg.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.INVISIBLE);
            }
        });
    }
}
