package com.example.android.sha;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VibratorTestActivity extends AppCompatActivity {

    Button checkVibrate, testEnd;
    TextView textVibrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator_test);

        checkVibrate = (Button) this.findViewById(R.id.checkVibrate);
        testEnd = (Button) this.findViewById(R.id.testEnd);
        textVibrate = (TextView) this.findViewById(R.id.textVibrate);

        final Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        if(vibrator.hasVibrator()){
            textVibrate.setText(R.string.vibratorTest);
            checkVibrate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vibrator.vibrate(1000);
                }
            });

        }else{
            textVibrate.setText("Este smarthphone no contiene modo vibratorio.");
        }

        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VibratorTestActivity.this, VibratorResultActivity.class));
            }
        });
    }
}
