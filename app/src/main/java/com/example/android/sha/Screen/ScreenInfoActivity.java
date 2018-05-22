package com.example.android.sha.Screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sha.R;

public class ScreenInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_info);

        Button button = (Button) findViewById(R.id.screenTest);
        TextView textView = (TextView) findViewById(R.id.text1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScreenInfoActivity.this,ScreenTestActivity.class));
            }
        });
    }
}
