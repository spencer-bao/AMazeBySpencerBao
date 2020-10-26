package edu.wm.cs.cs301.SpencerBao.gui;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

import java.util.ArrayList;
import java.util.List;

public class AMazeActivity extends AppCompatActivity {
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_title);

        seekBar = (SeekBar) seekBar.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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

        Spinner mazeSelectSpinner = (Spinner) findViewById(R.id.mazeSelectSpinner);
        ArrayAdapter<String> mazeSelectAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, mazeAlgorithms);
        mazeSelectSpinner.setAdapter(mazeSelectAdapter);

        mazeSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mazeSelectSpinner.setAdapter(mazeSelectAdapter);

        List<String> roomsNoRooms = new ArrayList<String>();
        mazeAlgorithms.add("Rooms");
        mazeAlgorithms.add("No Rooms");

        Spinner roomSpinner = (Spinner) findViewById(R.id.roomSpinner);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, roomsNoRooms);
        roomSpinner.setAdapter(roomAdapter);

        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        roomSpinner.setAdapter(roomAdapter);


    }
//    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



}
