package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

        Switch mapSwitch = (Switch) findViewById(R.id.animateMapSwitch);
        mapSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Map Switch: " + isChecked, Toast.LENGTH_SHORT).show();
                Log.v("Animat Map Switch", "Map Switch");
            }
        });

        Switch visibleSwitch = (Switch) findViewById(R.id.animatVisibleSwitch);
        visibleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Visible Switch: " + isChecked, Toast.LENGTH_SHORT).show();
                Log.v("Visible Switch", "Visible Switch");
            }
        });

        final SeekBar skillBar = (SeekBar) findViewById(R.id.animatSpeed);
        skillBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Changed speed",Toast.LENGTH_SHORT).show();
                Log.v("Animat Speed toast", "Changed Speed");
            }
        });
        /**
         * Button plays and stops the automatic robot driver.
         */
        final Button playPauseButton = (Button) findViewById(R.id.playPauseButton);
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playPauseButton.getText().equals("Pause")){
                    playPauseButton.setText("Start");
                    Toast.makeText(getApplicationContext(), "Pause", Toast.LENGTH_SHORT).show();
                    Log.v("Pause toast", "Pressed Pause");
                } else {
                    playPauseButton.setText("Pause");
                    Toast.makeText(getApplicationContext(), "Start", Toast.LENGTH_SHORT).show();
                    Log.v("Start toast", "Pressed Start");
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
                Toast.makeText(getApplicationContext(), "Go to Winning Screen", Toast.LENGTH_SHORT).show();
                Log.v("toWinning toast", "Go to Winning Screen");
                startActivity(next);
            }
        });

        Button go2LosingButton = (Button) findViewById(R.id.go2LosingButton);
        go2LosingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), LosingActivity.class);
                Toast.makeText(getApplicationContext(), "Go to Losing Screen", Toast.LENGTH_SHORT).show();
                Log.v("toLosing toast", "Go to Losing Screen");
                startActivity(next);
            }
        });

//        Button zoomInButton = (Button) findViewById(R.id.zoomInButton);
//        zoomInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Zoom in", Toast.LENGTH_SHORT).show();
//                Log.v("Zoom In Button", "Zoom In button");
//            }
//        });
//
//        Button zoomOutButton = (Button) findViewById(R.id.zoomOutButton);
//        zoomOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Zoom out", Toast.LENGTH_SHORT).show();
//                Log.v("Zoom Out Button", "Zoom Out button");
//            }
//        });

        /**
         * Get the robot config from GeneratingActivity and changes the sensor accordingly (Reliable,
         * Unreliable).
         */
        Intent intent = getIntent();
        String robotConfig = DataHolder.getRobotConfig();
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
        Toast.makeText(getApplicationContext(), "Back button", Toast.LENGTH_SHORT).show();
        Log.v("Back Button", "Back button");
    };
}
