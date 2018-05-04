package com.example.android.sha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CameraInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_info);

        TextView tvInfo = findViewById(R.id.info);
        Button test = findViewById(R.id.test);

        //VideoProfile.CameraCapabilities

        tvInfo.setText(Html.fromHtml("<p><b>Fabricante:</b>Sony</p><p><b>Megapixels:</b> 1000 Mpx</p>"));

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CameraInfoActivity.this, CameraTestActivity.class));
            }
        });


    }
}
