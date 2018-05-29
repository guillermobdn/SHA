package com.example.android.sha.Screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
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
        TextView tvInfo = (TextView) findViewById(R.id.info);

        DisplayMetrics metrics = getResources().getDisplayMetrics();


        tvInfo.setText(Html.fromHtml("<p><b>Densidad lógica: </b>"+ metrics.density +"</p><p><b>Densidad: </b>"
                + metrics.densityDpi + " pulgadas </p><p><b>Resolución: </b>"+ metrics.widthPixels + " x " +metrics.heightPixels
                + " píxeles </p><p><b>Píxeles por pulgada en la dimensión X: </b>" + metrics.xdpi
                + "</p><p><b>Píxeles por pulgada en la dimensión Y: </b>" + metrics.ydpi + "</p>"));
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScreenInfoActivity.this,ScreenTestActivity.class));
            }
        });
    }
}
