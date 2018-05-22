package com.example.android.sha.FlashLight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.R;

public class FlashlightInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight_info);

        Button test = findViewById(R.id.flashlightTest);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlashlightInfoActivity.this, FlashlightTestActivity.class));
            }
        });
    }
}
