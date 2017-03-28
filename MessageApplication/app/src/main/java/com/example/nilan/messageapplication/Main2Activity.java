package com.example.nilan.messageapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent myIntent = getIntent();
        text = (EditText) findViewById(R.id.editTextNewActivity);
        String msg = myIntent.getStringExtra("MY_MESSAGE");
        text.setText(msg);
    }
}
