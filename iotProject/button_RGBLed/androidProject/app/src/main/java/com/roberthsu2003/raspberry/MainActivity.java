package com.roberthsu2003.raspberry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
@IgnoreExtraProperties
class RGBLed{
    public int R;
    public int G;
    public int B;

    public RGBLed(){

    }

    public RGBLed(int r, int g, int b){
        R = r;
        G = g;
        B = b;
    }
}

public class MainActivity extends AppCompatActivity {
    private SeekBar rSeekBar;
    private SeekBar gSeekBar;
    private SeekBar bSeekBar;
    private TextView rTextView;
    private TextView gTextView;
    private TextView bTextView;
    private DatabaseReference rgbLedRef = FirebaseDatabase.getInstance().getReference("iot20191126/RGBLed");

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
                        MainActivity.this.rTextView.setText("R:" + progress);
                        break;
                    case R.id.greenBar:
                        MainActivity.this.gTextView.setText("G:" + progress);
                        break;

                    case R.id.blueBar:
                        MainActivity.this.bTextView.setText("B:" + progress);
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
                RGBLed led = new RGBLed(MainActivity.this.rSeekBar.getProgress(), MainActivity.this.gSeekBar.getProgress(), MainActivity.this.bSeekBar.getProgress());
                switch(seekBar.getId()){
                    case R.id.redBar:
                        led.R = seekBar.getProgress();
                        break;
                    case R.id.greenBar:
                        led.G = seekBar.getProgress();
                        break;

                    case R.id.blueBar:
                        led.B = seekBar.getProgress();
                        break;
                    default:
                        break;

                }

                MainActivity.this.rgbLedRef.setValue(led);



            }
        };
        rSeekBar.setOnSeekBarChangeListener(seekBarlistener);
        gSeekBar.setOnSeekBarChangeListener(seekBarlistener);
        bSeekBar.setOnSeekBarChangeListener(seekBarlistener);

        ValueEventListener ledListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("firebase","dataChange");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        rgbLedRef.addValueEventListener(ledListener);

    }
}
