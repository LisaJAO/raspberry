package com.roberthsu2003.raspberry;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "RELAYTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"reStart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

    public void user_click(View clickButton){
        Button btn = (Button) clickButton;
        if(btn.getText() == "OPEN"){
            btn.setText("CLOSE");
        }else {
            btn.setText("OPEN");
        }
    }
}
