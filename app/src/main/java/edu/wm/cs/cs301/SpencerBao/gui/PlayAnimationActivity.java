package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

public class PlayAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_animation);

        final Button playPauseButton = (Button) findViewById(R.id.playPauseButton);
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playPauseButton.getText().equals("Pause")){
                    playPauseButton.setText("Start");
                } else {
                    playPauseButton.setText("Pause");
                }
            }
        });

        Button go2WinningButton = (Button) findViewById(R.id.go2WinningButton);
        go2WinningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), WinningActivity.class);
                startActivity(next);
            }
        });

        Button go2LosingButton = (Button) findViewById(R.id.go2LosingButton);
        go2LosingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), LosingActivity.class);
                startActivity(next);
            }
        });

        Intent intent = getIntent();
        String robotConfig = intent.getStringExtra("Robot Config");
        ImageView image;
        switch (robotConfig){
            case "1111":
                break;
            case "1001":
                image = (ImageView) findViewById(R.id.sensorL);
                image.setImageResource(android.R.drawable.presence_busy);
                image = (ImageView) findViewById(R.id.sensorR);
                image.setImageResource(android.R.drawable.presence_busy);
                break;
            case "0110":
                image = (ImageView) findViewById(R.id.sensorF);
                image.setImageResource(android.R.drawable.presence_busy);
                image = (ImageView) findViewById(R.id.sensorB);
                image.setImageResource(android.R.drawable.presence_busy);
                break;
            case "0000":
                image = (ImageView) findViewById(R.id.sensorF);
                image.setImageResource(android.R.drawable.presence_busy);
                image = (ImageView) findViewById(R.id.sensorL);
                image.setImageResource(android.R.drawable.presence_busy);
                image = (ImageView) findViewById(R.id.sensorR);
                image.setImageResource(android.R.drawable.presence_busy);
                image = (ImageView) findViewById(R.id.sensorB);
                image.setImageResource(android.R.drawable.presence_busy);
                break;
        }
    }
}
