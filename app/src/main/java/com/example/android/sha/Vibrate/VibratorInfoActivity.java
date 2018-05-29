package com.example.android.sha.Vibrate;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.R;

public class VibratorInfoActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator_info);

        Button test = findViewById(R.id.vibratorTest);
        final Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        System.out.println("AAA " + vibrator.hasVibrator() + "" + vibrator.hasAmplitudeControl());
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VibratorInfoActivity.this, VibratorTestActivity.class));
            }
        });
    }
}
