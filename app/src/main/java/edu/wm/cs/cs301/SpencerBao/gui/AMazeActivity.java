package edu.wm.cs.cs301.SpencerBao.gui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

import java.util.ArrayList;
import java.util.List;

public class AMazeActivity extends AppCompatActivity {
//    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_title);

        final SeekBar skillBar = (SeekBar) findViewById(R.id.skillBar);
        skillBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView skillLevel = (TextView) findViewById(R.id.skillLevel);
                skillLevel.setText("Skill Level: " + progress);
                seekBar.setMax(9);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // referenced from Anupam Chugh at JournalDev.com
        List<String> mazeAlgorithms = new ArrayList<String>();
        mazeAlgorithms.add("DFS");
        mazeAlgorithms.add("Prim");
        mazeAlgorithms.add("Eller");

        final Spinner mazeSelectSpinner = (Spinner) findViewById(R.id.mazeSelectSpinner);
        ArrayAdapter<String> mazeSelectAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, mazeAlgorithms);
        mazeSelectSpinner.setAdapter(mazeSelectAdapter);
        mazeSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final List<String> roomsNoRooms = new ArrayList<String>();
        roomsNoRooms.add("Rooms");
        roomsNoRooms.add("No Rooms");

        final Spinner roomSpinner = (Spinner) findViewById(R.id.roomSpinner);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, roomsNoRooms);
        roomSpinner.setAdapter(roomAdapter);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button revisitButton = (Button) findViewById(R.id.revisitButton);
        revisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), GeneratingActivity.class);
                next.putExtra("Skill Level", skillBar.getProgress());
                startActivity(next);
            }
        });

        Button exploreButton = (Button) findViewById(R.id.exploreButton);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getApplicationContext(), GeneratingActivity.class);
                next.putExtra("Skill Level", skillBar.getProgress());
                next.putExtra("Maze Algorithm", mazeSelectSpinner.getSelectedItem().toString());
                next.putExtra("Rooms", roomSpinner.getSelectedItem().toString());
                startActivity(next);
            }
        });

    }
//    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }



}
