package com.roberthsu2003.raspberry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Mcp3008 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcp3008);
        setTitle("可變電阻");
    }
}
