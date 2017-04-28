package io.github.jb_aero.simplelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @authors James Bilbrey, Kately Seitz
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	Button register, login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		register = (Button) findViewById(R.id.button);
		login = (Button) findViewById(R.id.button2);

		register.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId())
		{
			case R.id.button:
				intent = new Intent(this, RegisterActivity.class);
				startActivity(intent);
				break;
			case R.id.button2:
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}
	}
}
