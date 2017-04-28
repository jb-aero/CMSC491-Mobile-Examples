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
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

	private DatabaseReference mydb;
	private FirebaseAuth mauth;
	EditText name, email, password;
	Button register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		mauth = FirebaseAuth.getInstance();
		mauth.signOut();
		name = (EditText) findViewById(R.id.editText4);
		email = (EditText) findViewById(R.id.editText5);
		password = (EditText) findViewById(R.id.editText6);
		register = (Button) findViewById(R.id.button3);
		mydb = FirebaseDatabase.getInstance().getReference().child("icaUsers");
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.button3:
				mauth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
						addOnCompleteListener(new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {
								if (task.isSuccessful()) {
									signIn(email, password, name);
								} else {
									Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_LONG).show();
									Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
								}
							}
						});
				break;
		}
	}

	private void signIn(EditText email, EditText password, EditText name) {
		signIn(email.getText().toString(), password.getText().toString(), name.getText().toString());
	}

	private void signIn(final String email, String password, final String name) {
		mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				if (task.isSuccessful()) {
					User user = new User(email, name);
					mydb.child(User.emailToDB(email)).setValue(user);
					Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
