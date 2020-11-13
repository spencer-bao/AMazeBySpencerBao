package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

/**
 * Responsibilities: Displays the maze and lets the user manually navigate through the maze. Options
 * to toggle the map, toggle visible walls, and to scale the size of the map. Has up, down, left,
 * right buttons.
 * <p></p>
 * Classes: GeneratingActivity, WinningActivity, LosingActivity
 * <p></p>
 * @Author Spencer Bao
 */
public class PlayManuallyActivity extends AppCompatActivity {
    Constants.UserInput userInput = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_manually);
        final MazePanel panel = (MazePanel) findViewById(R.id.manualMazePanel);
        final StatePlaying statePlaying = new StatePlaying();
        statePlaying.setMazeConfiguration(DataHolder.getMazeConfig());
        statePlaying.start(panel);

        Button upButton = (Button) findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Up button", Toast.LENGTH_SHORT).show();
                Log.v("Up Button", "Up button");
                userInput = Constants.UserInput.Up;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();

            }
        });

        Button downButton = (Button) findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Down button", Toast.LENGTH_SHORT).show();
                Log.v("Down Button", "Down button");
                userInput = Constants.UserInput.Down;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Button rightButton = (Button) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Right button", Toast.LENGTH_SHORT).show();
                Log.v("Right Button", "Right button");
                userInput = Constants.UserInput.Right;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Button leftButton = (Button) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Left button", Toast.LENGTH_SHORT).show();
                Log.v("Left Button", "Left button");
                userInput = Constants.UserInput.Left;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Button shortcutButton = (Button) findViewById(R.id.shortcutButton);
        shortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), WinningActivity.class);
                Toast.makeText(getApplicationContext(), "Shortcut button", Toast.LENGTH_SHORT).show();
                Log.v("Shortcut Button", "Shortcut button");
                startActivity(next);
            }
        });

        Button zoomInButton = (Button) findViewById(R.id.zoomInButton);
        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Zoom in", Toast.LENGTH_SHORT).show();
                Log.v("Zoom In Button", "Zoom In button");
                userInput = Constants.UserInput.ZoomIn;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Button zoomOutButton = (Button) findViewById(R.id.zoomOutButton);
        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Zoom out", Toast.LENGTH_SHORT).show();
                Log.v("Zoom Out Button", "Zoom Out button");
                userInput = Constants.UserInput.ZoomOut;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Switch mapSwitch = (Switch) findViewById(R.id.mapSwitch);
        mapSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Map Switch: " + isChecked, Toast.LENGTH_SHORT).show();
                Log.v("Map Switch", "Map Switch");
                userInput = Constants.UserInput.ToggleFullMap;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Switch solutionSwitch = (Switch) findViewById(R.id.solutionSwitch);
        solutionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Solution Switch: " + isChecked, Toast.LENGTH_SHORT).show();
                Log.v("Solution Switch", "Solution Switch");
                userInput = Constants.UserInput.ToggleSolution;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });

        Switch visibleSwitch = (Switch) findViewById(R.id.visibleSwitch);
        visibleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Visible Switch: " + isChecked, Toast.LENGTH_SHORT).show();
                Log.v("Visible Switch", "Visible Switch");
                userInput = Constants.UserInput.ToggleLocalMap;
                statePlaying.keyDown(userInput, 0);
                panel.invalidate();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        startActivity(back2Title);
        Toast.makeText(getApplicationContext(), "Back button", Toast.LENGTH_SHORT).show();
        Log.v("Back Button", "Back button");
    };
}
