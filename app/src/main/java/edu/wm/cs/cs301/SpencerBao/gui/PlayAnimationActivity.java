package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

/**
 * Responsibilities: Displays the maze and lets the user watch a robot explore the maze. Displays
 * remaining energy. Options to toggle the map and to scale the size of the map. Has a start/pause
 * button.
 * <p></p>
 * Classes: GeneratingActivity, WinningActivity, LosingActivity
 * <p></p>
 * @Author Spencer Bao
 */
public class PlayAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_animation);

        /**
         * Button plays and stops the automatic robot driver.
         */
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

        /**
         * Placeholders for the animation, sends user directly to victory and defeat screens.
         */
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

        /**
         * Get the robot config from GeneratingActivity and changes the sensor accordingly (Reliable,
         * Unreliable).
         */
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

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        startActivity(back2Title);
    };
}
