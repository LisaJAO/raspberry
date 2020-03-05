package com.roberthsu2003.raspberry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
        gTextView = (TextView) findViewById(R.id.greenTextView);
        bTextView = (TextView) findViewById(R.id.blueTextView);
        rTextView.setText("R:" + rSeekBar.getProgress());
        gTextView.setText("G:" + rSeekBar.getProgress());
        bTextView.setText("B:" + rSeekBar.getProgress());
        SeekBar.OnSeekBarChangeListener seekBarlistener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch(seekBar.getId()){
                    case R.id.redBar:
                        Log.d("SeekBar","red");
                        break;
                    case R.id.greenBar:
                        Log.d("SeekBar","green");
                        break;

                    case R.id.blueBar:
                        Log.d("SeekBar","blue");
                        break;
                    default:
                        break;

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                switch(seekBar.getId()){
                    case R.id.redBar:
                        Log.d("SeekBar","red");
                        break;
                    case R.id.greenBar:
                        Log.d("SeekBar","green");
                        break;

                    case R.id.blueBar:
                        Log.d("SeekBar","blue");
                        break;
                    default:
                        break;

                }

            }
        };
        rSeekBar.setOnSeekBarChangeListener(seekBarlistener);
        gSeekBar.setOnSeekBarChangeListener(seekBarlistener);
        bSeekBar.setOnSeekBarChangeListener(seekBarlistener);

    }
}
