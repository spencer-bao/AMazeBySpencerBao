package edu.wm.cs.cs301.SpencerBao.gui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        driverSelect.add("Manual");
        driverSelect.add("Wall Follower");
        driverSelect.add("Wizard");

        Spinner driverSelectSpinner = (Spinner) findViewById(R.id.driverSelect);
        ArrayAdapter<String> driverSelectAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, driverSelect);
        driverSelectSpinner.setAdapter(driverSelectAdapter);
        driverSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // robotConfig spinner
        List<String> robotConfig = new ArrayList<String>();
        robotConfig.add("Premium");
        robotConfig.add("Mediocre");
        robotConfig.add("So-so");
        robotConfig.add("Shaky");

        Spinner robotConfigSpinner = (Spinner) findViewById(R.id.robotConfig);
        ArrayAdapter<String> robotConfigAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, robotConfig);
        robotConfigSpinner.setAdapter(robotConfigAdapter);
        robotConfigAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
