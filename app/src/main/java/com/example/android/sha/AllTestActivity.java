package com.example.android.sha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.Camera.CameraInfoActivity;
import com.example.android.sha.Camera.CameraTestActivity;

public class AllTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_test);


        Button button;

        button = (Button) findViewById(R.id.button);

        final Intent intent = new Intent(AllTestActivity.this, CameraTestActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
