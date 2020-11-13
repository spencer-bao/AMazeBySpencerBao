package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import edu.wm.cs.cs301.SpencerBao.generation.Maze;
import edu.wm.cs.cs301.SpencerBao.generation.MazeBuilder;
import edu.wm.cs.cs301.SpencerBao.generation.MazeFactory;
import edu.wm.cs.cs301.SpencerBao.generation.Order;
import edu.wm.cs.cs301.SpencerBao.generation.StubOrder;

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
public class GeneratingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MazeFactory mazeFactory = new MazeFactory();
        final StubOrder order = new StubOrder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_generating);

        // driverSelect spinner
        List<String> driverSelect = new ArrayList<String>();
        driverSelect.add("[Select Driver]");
        driverSelect.add("Manual");
        driverSelect.add("Wall Follower");
        driverSelect.add("Wizard");

        final Spinner driverSelectSpinner = (Spinner) findViewById(R.id.driverSelect);
        ArrayAdapter<String> driverSelectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, driverSelect);
        driverSelectSpinner.setAdapter(driverSelectAdapter);
        driverSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driverSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected: " +
                        driverSelectSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Log.v("Driver Select toast", "Selected" + driverSelectSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // robotConfig spinner
        final List<String> robotConfig = new ArrayList<String>();
        robotConfig.add("[Robot Config]");
        robotConfig.add("Premium");
        robotConfig.add("Mediocre");
        robotConfig.add("So-so");
        robotConfig.add("Shaky");

        final Spinner robotConfigSpinner = (Spinner) findViewById(R.id.robotConfig);
        ArrayAdapter<String> robotConfigAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, robotConfig);
        robotConfigSpinner.setAdapter(robotConfigAdapter);
        robotConfigAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        robotConfigSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected: " +
                        robotConfigSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Log.v("Robot Config toast", "Selected" + robotConfigSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * Runs a thread that increments the loading bar with some pause in between. When the loading
         * bar is at 100 and the driver and robot configs are selected from their spinners,
         * then the generating screen can continue.
         */
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBar.getProgress() < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.incrementProgressBy(10);
                }

                while (progressBar.getProgress() != 100 ||
                        driverSelectSpinner.getSelectedItem().toString() == "[Select Driver]" ||
                        robotConfigSpinner.getSelectedItem().toString() == "[Robot Config]") {
                    // do nothing if there has been no selection
                }

                Intent next;
                if (driverSelectSpinner.getSelectedItem().toString() == "Manual") {
                    next = new Intent(getApplicationContext(), PlayManuallyActivity.class);
                } else {
                    next = new Intent(getApplicationContext(), PlayAnimationActivity.class);
                }
                DataHolder.setDriverConfig(driverSelectSpinner.getSelectedItem().toString());

                switch (robotConfigSpinner.getSelectedItem().toString()) {
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
                DataHolder.setRobotConfig(robotConfigSpinner.getSelectedItem().toString());
                order.setSkillLevel(DataHolder.getSkillLevel());
                switch (DataHolder.getMazeAlgorithm()) {
                    case "DFS":
                        order.setBuilder(Order.Builder.DFS);
                        break;
                    case "Eller":
                        order.setBuilder(Order.Builder.Eller);
                        break;
                    case "Prim":
                        order.setBuilder(Order.Builder.Prim);
                        break;
                }
                mazeFactory.order(order);
                mazeFactory.waitTillDelivered();
                DataHolder.setMazeConfig(order.getMazeConfig());
                startActivity(next);
            }
        }).start();
    }

    @Override
    public void onBackPressed(){
        Intent back2Title = new Intent(getApplicationContext(), AMazeActivity.class);
        Toast.makeText(getApplicationContext(), "Back button", Toast.LENGTH_SHORT).show();
        Log.v("Back Button", "Back button");
        startActivity(back2Title);

    };

}
