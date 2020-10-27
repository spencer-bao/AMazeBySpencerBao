package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibilities: Displays an intermediate page where the user can select the driver and robot
 * settings while a maze is generated with a background thread. The page informs the user on the
 * progress that is made on the preparation of a maze. The design idea is to keep the user engaged
 * while the game computes the maze to reduce the perceived wait time.
 * <p></p>
 * Classes: AMazeActivity, PlayManuallyActivity, PlayAnimationActivity
 * <p></p>
 * @Author Spencer Bao
 */
public class GeneratingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_generating);

        // driverSelect spinner
        List<String> driverSelect = new ArrayList<String>();
        driverSelect.add("[Select Driver]");
        driverSelect.add("Manual");
        driverSelect.add("Wall Follower");
        driverSelect.add("Wizard");

        final Spinner driverSelectSpinner = (Spinner) findViewById(R.id.driverSelect);
        ArrayAdapter<String> driverSelectAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, driverSelect);
        driverSelectSpinner.setAdapter(driverSelectAdapter);
        driverSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // robotConfig spinner
        final List<String> robotConfig = new ArrayList<String>();
        robotConfig.add("[Robot Config]");
        robotConfig.add("Premium");
        robotConfig.add("Mediocre");
        robotConfig.add("So-so");
        robotConfig.add("Shaky");

        final Spinner robotConfigSpinner = (Spinner) findViewById(R.id.robotConfig);
        ArrayAdapter<String> robotConfigAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, robotConfig);
        robotConfigSpinner.setAdapter(robotConfigAdapter);
        robotConfigAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /**
         * Runs a thread that increments the loading bar with some pause in between. When the loading
         * bar is at 100 and the driver and robot configs are selected from their spinners,
         * then the generating screen can continue.
         */
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressBar.getProgress() < 100) {
                        Thread.sleep(500);
                        progressBar.incrementProgressBy(10);
                    }
                    Log.d("tag", String.valueOf(progressBar.getProgress()));
                    while (progressBar.getProgress() != 100 ||
                            driverSelectSpinner.getSelectedItem().toString() == "[Select Driver]" ||
                            robotConfigSpinner.getSelectedItem().toString() == "[Robot Config]"){
                    }

                    Intent next;
                    if (driverSelectSpinner.getSelectedItem().toString() == "Manual"){
                        next = new Intent(getApplicationContext(), PlayManuallyActivity.class);
                    } else{
                        next = new Intent(getApplicationContext(), PlayAnimationActivity.class);
                    }
                    next.putExtra("Driver", driverSelectSpinner.getSelectedItem().toString());

                    switch (robotConfigSpinner.getSelectedItem().toString()){
                        case "Premium":
                            next.putExtra("Robot Config", "1111");
                            break;
                        case "Mediocre":
                            next.putExtra("Robot Config", "1001");
                            break;
                        case "So-so":
                            next.putExtra("Robot Config", "0110");
                            break;
                        case "Shaky":
                            next.putExtra("Robot Config", "0000");
                            break;
                    }
                    startActivity(next);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        startActivity(back2Title);
    };
}
