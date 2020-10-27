package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

import java.util.ArrayList;
import java.util.List;

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
        List<String> robotConfig = new ArrayList<String>();
        robotConfig.add("[Robot Config]");
        robotConfig.add("Premium");
        robotConfig.add("Mediocre");
        robotConfig.add("So-so");
        robotConfig.add("Shaky");

        final Spinner robotConfigSpinner = (Spinner) findViewById(R.id.robotConfig);
        ArrayAdapter<String> robotConfigAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, robotConfig);
        robotConfigSpinner.setAdapter(robotConfigAdapter);
        robotConfigAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // progressBar
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
                    while (progressBar.getProgress() != 100 || driverSelectSpinner.getSelectedItem() == "[Select Driver]"
                            || robotConfigSpinner.getSelectedItem() == "[Maze Config]"){

                    }
                    if (driverSelectSpinner.getSelectedItem() == "Manual Driver"){
                        Intent next = new Intent(getApplicationContext(), PlayManuallyActivity.class);
                        startActivity(next);
                    } else{
                        Intent next = new Intent(getApplicationContext(), PlayAnimationActivity.class);
                        startActivity(next);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
