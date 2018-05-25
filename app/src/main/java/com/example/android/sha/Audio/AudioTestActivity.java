package com.example.android.sha.Audio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sha.R;

public class AudioTestActivity extends AppCompatActivity {

    Button play_pause,testEnd;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_test);

        play_pause = (Button) findViewById(R.id.play_pause);
        testEnd = (Button) findViewById(R.id.testEnd);

        mp = MediaPlayer.create(this, R.raw.song);


        play_pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                }else {
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                }

            }
        });


        testEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                play_pause.setBackgroundResource(R.drawable.play);
                startActivity(new Intent(AudioTestActivity.this, AudioResultActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        play_pause.setBackgroundResource(R.drawable.play);
        mp.pause();
    }

}
