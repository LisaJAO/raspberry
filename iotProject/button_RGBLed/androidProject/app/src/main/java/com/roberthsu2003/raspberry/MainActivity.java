package com.roberthsu2003.raspberry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private SeekBar rSeekBar;
    private SeekBar gSeekBar;
    private SeekBar bSeekBar;
    private TextView rTextView;
    private TextView gTextView;
    private TextView bTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rSeekBar = (SeekBar) findViewById(R.id.redBar);
        gSeekBar = (SeekBar) findViewById(R.id.greenBar);
        bSeekBar = (SeekBar) findViewById(R.id.blueBar);
        rTextView = (TextView) findViewById(R.id.redTextView);
        gTextView = (TextView) findViewById(R.id.GreenTextView);
        bTextView = (TextView) findViewById(R.id.blueTextView);

    }
}
