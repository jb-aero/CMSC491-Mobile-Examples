package io.github.jb_aero.myfcmtest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private FirebaseAuth mauth;
	EditText email, password;
	Button register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mauth = FirebaseAuth.getInstance();
		mauth.signOut();
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.register:
				mauth.signInWithEmailAndPassword(email.toString(), password.toString()).
						addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {

					}
				});
				mauth.createUserWithEmailAndPassword(email.toString(), password.toString()).
						addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful())
						{
							Toast.makeText(MainActivity.this, "Registration was successful!", Toast.LENGTH_LONG).show();
						}
						else
						{
							Toast.makeText(MainActivity.this, "Registration failed!", Toast.LENGTH_LONG).show();
						}
					}
				});
				break;
		}
	}
}
