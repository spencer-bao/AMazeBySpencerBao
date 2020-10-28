package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

/**
 * Responsibilities: Displays victory, shows overall energy consumption, length of path taken, and
 * length of shortest possible length.
 * <p></p>
 * Classes: PlayAnimationActivity, PlayManuallyActivity
 * <p></p>
 * @Author Spencer Bao
 */
public class WinningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_winning);
    }

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        startActivity(back2Title);
        Toast.makeText(getApplicationContext(), "Back button", Toast.LENGTH_SHORT).show();
        Log.v("Back Button", "Back button");
    };
}
