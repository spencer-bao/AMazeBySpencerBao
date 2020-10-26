package edu.wm.cs.cs301.SpencerBao.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazebyspencerbao.R;

public class PlayManuallyActivity extends AppCompatActivity {
//    SeekBar seekBar;

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
}
