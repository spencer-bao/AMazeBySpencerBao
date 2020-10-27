package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_manually);

        Button shortcutButton = (Button) findViewById(R.id.shortcutButton);
        shortcutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), WinningActivity.class);
                startActivity(next);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        startActivity(back2Title);
    };
}
