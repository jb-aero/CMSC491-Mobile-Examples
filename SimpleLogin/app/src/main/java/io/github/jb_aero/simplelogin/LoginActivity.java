package io.github.jb_aero.simplelogin;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @authors James Bilbrey, Kately Seitz
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

	EditText email, password;
	Button login;
	private FirebaseAuth mauth;
	private DatabaseReference mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email = (EditText) findViewById(R.id.editText);
		password = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button4);
		mauth = FirebaseAuth.getInstance();
		mauth.signOut();
		mydb = FirebaseDatabase.getInstance().getReference().child("icaUsers");
		login.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.button4:
				mauth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
						addOnCompleteListener(new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {
								if (task.isSuccessful())
								{
									Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
									startActivity(intent);
								} else
								{
									Toast.makeText(LoginActivity.this, "Credentials not valid, please try again.", Toast.LENGTH_LONG).show();
								}
							}
						});
		}
	}
}
