package io.github.jb_aero.firstapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String username = intent.getStringExtra(MainActivity.USERNAME);

        TextView display = new TextView(this);
        display.setTextSize(40);
        display.setText("I'm sorry, " + username + ", I can't let you do that...");

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_profile);
        layout.addView(display);
    }
}
